package com.saurav.apnidukan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Product;

import java.util.List;

public class OrderedProductStatusAdapter extends RecyclerView.Adapter<OrderedProductStatusAdapter.OrderedProductStatusViewHolder> {
    Context context;
    List<Product> productList;

    public OrderedProductStatusAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OrderedProductStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ordered_product_details_rt, parent, false);
        return new OrderedProductStatusViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderedProductStatusAdapter.OrderedProductStatusViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.brandName.setText(product.getBrandName());
        holder.description.setText(product.getDescription());
        holder.price.setText(Integer.toString(product.getPrice()));
        Glide.with(context).load(product.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class OrderedProductStatusViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView brandName, description, price, status;
        public OrderedProductStatusViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.opImageView);
            brandName = itemView.findViewById(R.id.opBrandName);
            description = itemView.findViewById(R.id.opDescription);
            price = itemView.findViewById(R.id.opPriceTextView);
        }
    }
}
