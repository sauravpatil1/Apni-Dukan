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
import com.saurav.apnidukan.model.Shop;

import java.util.List;

public class LocalShopAdapter extends RecyclerView.Adapter<LocalShopAdapter.LocalShopViewHolder> {
    Context context;
    List<Shop> shopList;

    public LocalShopAdapter(Context context, List<Shop> shopList) {
        this.context = context;
        this.shopList = shopList;
    }

    @NonNull
    @Override
    public LocalShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.local_store_near_me_rt, parent, false);
        return new LocalShopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocalShopAdapter.LocalShopViewHolder holder, int position) {
        holder.name.setText(shopList.get(position).getName());
        holder.address.setText(shopList.get(position).getAddress());
        Glide.with(context).load(shopList.get(position).getImageURL()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return shopList.size();
    }

    public static class LocalShopViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name, address;
        public LocalShopViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.shopImageTextView);
            name = itemView.findViewById(R.id.shopNameTextView);
            address = itemView.findViewById(R.id.shopAddressTextView);
        }
    }
}
