package com.saurav.apnidukan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.saurav.apnidukan.adapter.OrderedProductAdapter;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsActivity extends AppCompatActivity {
    RecyclerView oderProductRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        oderProductRecyclerView = findViewById(R.id.orderedProductRecyclerView);
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(R.drawable.discounted, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted1, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted2, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted3, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted4, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted, 43, "safola", "Good quality oil"));
        productList.add(new Product(R.drawable.discounted1, 43, "safola", "Good quality oil"));
        setOderProductRecyclerView(productList);
    }
    public void setOderProductRecyclerView(List<Product> productList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        oderProductRecyclerView.setLayoutManager(manager);
        OrderedProductAdapter adapter = new OrderedProductAdapter(this, productList);
        oderProductRecyclerView.setAdapter(adapter);
    }
}