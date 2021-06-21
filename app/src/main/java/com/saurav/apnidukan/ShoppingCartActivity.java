package com.saurav.apnidukan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.saurav.apnidukan.adapter.ShoppingCartProductAdapter;
import com.saurav.apnidukan.model.Product;
import com.saurav.apnidukan.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView cartProductRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        cartProductRecyclerView = findViewById(R.id.cartProductRecyclerView);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.discounted, 434, "Safola","Good Quality"));
        productList.add(new Product(R.drawable.discounted1, 434, "Safola","Good Quality"));
        productList.add(new Product(R.drawable.discounted2, 434, "Safola","Good Quality"));
        productList.add(new Product(R.drawable.discounted3, 434, "Safola","Good Quality"));
        productList.add(new Product(R.drawable.discounted4, 434, "Safola","Good Quality"));
        productList.add(new Product(R.drawable.discounted, 434, "Safola","Good Quality"));
        for(Product p : productList){
            p.setShop(new Shop("Arihant Super Shop"));
        }
        setCartProductRecyclerView(productList);
    }
    public void setCartProductRecyclerView(List<Product> productList){
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        cartProductRecyclerView.setLayoutManager(layoutManager);
        cartProductRecyclerView.setNestedScrollingEnabled(false);
        ShoppingCartProductAdapter adapter = new ShoppingCartProductAdapter(this, productList);
        cartProductRecyclerView.setAdapter(adapter);
    }
}