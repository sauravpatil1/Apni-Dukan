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

import java.util.List;

public class SearchProductAdapter extends RecyclerView.Adapter<SearchProductAdapter.SearchProductViewHolder> {
    Context context;
    List<Product> productList;

    public SearchProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public SearchProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_product_rt, parent, false);
        return new SearchProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchProductAdapter.SearchProductViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.brandName.setText(p.getBrandName());
        holder.description.setText(p.getDescription());
        holder.price.setText(Integer.toString(p.getPrice())+"₹");
        holder.discount.setText(Integer.toString(p.getDiscount())+"%off");
        holder.shopName.setText(p.getShopName());
        Glide.with(context).load(p.getImage()).into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("PRODUCT_ID", p.getProductId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class SearchProductViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView brandName, shopName, description, price, discount;
        public SearchProductViewHolder(@NonNull View itemView) {
            super(itemView);
            brandName = itemView.findViewById(R.id.sProductBrandName);
            shopName = itemView.findViewById(R.id.sProductShopName);
            description = itemView.findViewById(R.id.sProductDescription);
            price = itemView.findViewById(R.id.sProductPrice);
            discount = itemView.findViewById(R.id.sProductDiscount);
            image = itemView.findViewById(R.id.sProductImage);
        }
    }
}
