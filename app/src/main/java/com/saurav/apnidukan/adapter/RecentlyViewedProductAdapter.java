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

import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Product;

import java.util.List;

public class RecentlyViewedProductAdapter extends RecyclerView.Adapter<RecentlyViewedProductAdapter.RecentlyViewedViewHolder> {
    Context context;
    List<Product> recentlyViewedList;

    public RecentlyViewedProductAdapter(Context context, List<Product> recentlyViewedList) {
        this.context = context;
        this.recentlyViewedList = recentlyViewedList;
    }

    @NonNull
    @Override
    public RecentlyViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_recently_viewed_rt, parent, false);
        return new RecentlyViewedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  RecentlyViewedProductAdapter.RecentlyViewedViewHolder holder, int position) {
        holder.imageView.setImageResource(recentlyViewedList.get(position).getImageURL());
        holder.productNameTextView.setText(recentlyViewedList.get(position).getBrandName());
    }

    @Override
    public int getItemCount() {
        return recentlyViewedList.size();
    }

    public static class RecentlyViewedViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView productNameTextView;
        public RecentlyViewedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recentlyViewedItemsImageView);
            productNameTextView = itemView.findViewById(R.id.productNameTextView);
        }
    }
}

