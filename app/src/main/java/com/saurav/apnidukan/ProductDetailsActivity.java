package com.saurav.apnidukan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.databinding.ActivityProductDetailsBinding;
import com.saurav.apnidukan.model.Product;

public class ProductDetailsActivity extends AppCompatActivity {
    ActivityProductDetailsBinding binding;
    DatabaseReference dbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dbRef = FirebaseDatabase.getInstance().getReference("product");
        Intent callerIntent = getIntent();
        String productId = callerIntent.getStringExtra("PRODUCT_ID");
        Query query = dbRef.orderByChild("productId").equalTo(productId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Product product = null;
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    product = dataSnapshot.getValue(Product.class);
                }
                binding.pdBrandName.setText(product.getBrandName());
                binding.pdPriceTextView.setText(Integer.toString(product.getPrice()));
                binding.pdDiscount.setText(Integer.toString(product.getDiscount()));
                binding.pdDescriptionTextView.setText(product.getDescription());
                binding.pdTypeTextView.setText(product.getType());
                binding.pdShopNameTextView.setText(product.getShopName());
                Glide.with(ProductDetailsActivity.this).load(product.getImage()).into(binding.pdImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProductDetailsActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}