package com.saurav.apnidukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.adapter.OrderedProductStatusAdapter;
import com.saurav.apnidukan.model.Order;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class MyOrdersActivity extends AppCompatActivity {
    RecyclerView myProductRecyclerView;
    OrderedProductStatusAdapter adapter;
    List<Product> productList;
    List<Order> orderList;

    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);
        myProductRecyclerView = findViewById(R.id.myOrdersRecyclerView);
        myProductRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        productList = new ArrayList<>();
        orderList = new ArrayList<>();
        adapter = new OrderedProductStatusAdapter(this, productList);
        myProductRecyclerView.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("order");
        Query query = dbRef.orderByChild("userId").equalTo(MainActivity.currentUser.getId());

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Order order = ds.getValue(Order.class);
                    orderList.add(order);
                }
                DatabaseReference prodRef = FirebaseDatabase.getInstance().getReference("product");
                for(int i=0;i<orderList.size();i++){
                    Query prodQuery = prodRef.orderByChild("productId").equalTo(orderList.get(i).getProductId());
                    prodQuery.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot ds : snapshot.getChildren()){
                                Product product = ds.getValue(Product.class);
                                productList.add(product);
                            }
                            //Toast.makeText(MyOrdersActivity.this, Integer.toString(orderList.size()), Toast.LENGTH_LONG).show();
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(MyOrdersActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MyOrdersActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}