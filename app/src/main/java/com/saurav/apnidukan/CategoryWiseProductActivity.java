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
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.adapter.SearchProductAdapter;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryWiseProductActivity extends AppCompatActivity {
    DatabaseReference dbRef;
    RecyclerView productRecyclerView;
    SearchProductAdapter adapter;
    List<Product> productList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_wise_product);
        productRecyclerView = findViewById(R.id.categoryProductRV);
        productRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        Intent callingIntent = getIntent();
        String category = callingIntent.getStringExtra("TYPE");
        productList = new ArrayList<>();
        adapter = new SearchProductAdapter(CategoryWiseProductActivity.this, productList);
        productRecyclerView.setAdapter(adapter);
        dbRef = FirebaseDatabase.getInstance().getReference("product");
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Product product = dataSnapshot.getValue(Product.class);
                    if(product.getType().equals(category))productList.add(product);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(CategoryWiseProductActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}