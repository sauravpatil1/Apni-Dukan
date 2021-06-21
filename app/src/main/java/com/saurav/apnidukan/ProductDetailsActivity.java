package com.saurav.apnidukan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailsActivity extends AppCompatActivity {
    ImageView productImageView, backImageView;
    TextView productNameTextView, productPriceTextView, productDescriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        //String description = intent.getStringExtra("description");
        //String price = intent.getStringExtra("price");
        Integer image = intent.getIntExtra("imageURL", 0);
        productImageView = findViewById(R.id.productImageView);
        productImageView.setImageResource(image);
        productNameTextView = findViewById(R.id.productDetailsNameTextView);
        productNameTextView.setText(name);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        //productPriceTextView.setText(price);
        productDescriptionTextView = findViewById(R.id.productDescriptionTextView);
        //productDescriptionTextView.setText(description);
        backImageView = findViewById(R.id.backImageView);
        backImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ProductDetailsActivity.this, MainActivity.class);
                startActivity(intent1);
            }
        });
    }
}