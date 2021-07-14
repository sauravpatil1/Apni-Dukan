package com.saurav.apnidukan.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.GridSpacingItemDecoration;
import com.saurav.apnidukan.MainActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.CategoryAdapter;
import com.saurav.apnidukan.adapter.DiscountedProductAdapter;
import com.saurav.apnidukan.adapter.RecentlyViewedProductAdapter;
import com.saurav.apnidukan.adapter.TopProductAdapter;
import com.saurav.apnidukan.adapter.WishListProductAdapter;
import com.saurav.apnidukan.model.Category;
import com.saurav.apnidukan.model.DiscountedProduct;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LOCATION_SERVICE;


public class HomeFragment extends Fragment {
    RecyclerView categoryRecyclerView, productRecyclerView;
    List<Product> productList;
    TopProductAdapter adapter;
    private DatabaseReference dbRef;
    public HomeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        productRecyclerView = view.findViewById(R.id.homeTopProductRecyclerView);

        List<Category> categoryList = new ArrayList<>();
        productList = new ArrayList<>();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(16), true));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter= new TopProductAdapter(getContext(), productList);
        productRecyclerView.setAdapter(adapter);

        setProductRecyclerView();

        categoryList.add(new Category(1, R.drawable.category1, "Can Food"));
        categoryList.add(new Category(2, R.drawable.category2, "Milk"));
        categoryList.add(new Category(3, R.drawable.category3, "Fruits"));
        categoryList.add(new Category(4, R.drawable.category4, "Vegetables"));
        categoryList.add(new Category(5, R.drawable.category5, "Alcohols"));
        categoryList.add(new Category(6, R.drawable.category6, "Meat"));
        categoryList.add(new Category(7, R.drawable.category7, "Packed Food"));
        categoryList.add(new Category(8, R.drawable.category8, "Toiletries"));
        setCategoryRecyclerView(categoryList);

        return view;
    }

    public void setCategoryRecyclerView(List<Category> categoryList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(manager);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), categoryList);
        categoryRecyclerView.setAdapter(adapter);
    }
    public void setProductRecyclerView(){
        dbRef = FirebaseDatabase.getInstance().getReference("product");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
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

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}