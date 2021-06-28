package com.saurav.apnidukan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.MainActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.OrderedProductAdapter;
import com.saurav.apnidukan.model.Order;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class PendingOrdersFragment extends Fragment {
    RecyclerView productDisplayRecyclerView;
    OrderedProductAdapter adapter;
    List<Product> productList;
    List<Order> orderList;

    DatabaseReference dbRef;
    public PendingOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_orders, container, false);

        productDisplayRecyclerView = view.findViewById(R.id.orderRecyclerView);
        productList = new ArrayList<>();
        orderList = new ArrayList<>();
        productDisplayRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new OrderedProductAdapter(getContext(), productList);
        productDisplayRecyclerView.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("order");
        Query query = dbRef.orderByChild("shopId").equalTo(MainActivity.currentUser.getShopId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot ds : snapshot.getChildren()){
                    Order order = ds.getValue(Order.class);
                    orderList.add(order);
                }
                DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference("product");
                for(int i=0;i<orderList.size();i++) {
                    if(!orderList.get(i).getStatus().equals("placed"))continue;
                    Query prodQuery = prodRef.orderByChild("productId").equalTo(orderList.get(i).getProductId());
                    prodQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot ds : snapshot.getChildren()){
                                Product product = ds.getValue(Product.class);
                                productList.add(product);
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}