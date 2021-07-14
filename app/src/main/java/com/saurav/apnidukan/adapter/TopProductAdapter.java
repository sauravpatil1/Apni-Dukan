package com.saurav.apnidukan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saurav.apnidukan.ProductDetailsActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class TopProductAdapter extends RecyclerView.Adapter<TopProductAdapter.TopProductViewHolder> {
    Context context;
    List<Product> productList;

    public TopProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public TopProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.top_product_rt, parent, false);
        return new TopProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopProductAdapter.TopProductViewHolder holder, int position) {
        Product product = productList.get(position);
        Glide.with(context).load(product.getImage()).into(holder.productImage);
        holder.name.setText(product.getBrandName());
        holder.price.setText(Integer.toString(product.getPrice())+"₹");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("PRODUCT_ID", product.getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class TopProductViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        TextView name, price;
        public TopProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.topProductImageView);
            name = itemView.findViewById(R.id.topProductBrandName);
            price = itemView.findViewById(R.id.topProductPrice);
        }
    }
}
