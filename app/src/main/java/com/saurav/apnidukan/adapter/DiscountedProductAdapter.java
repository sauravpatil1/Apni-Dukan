package com.saurav.apnidukan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.DiscountedProduct;

import java.util.List;

public class DiscountedProductAdapter extends RecyclerView.Adapter<DiscountedProductAdapter.DiscountedProductViewHolder> {
    Context context;
    List<DiscountedProduct> discountedProductList;

    public DiscountedProductAdapter(Context context, List<DiscountedProduct> discountedProductList) {
        this.context = context;
        this.discountedProductList = discountedProductList;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_discounted_product_rt, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductAdapter.DiscountedProductViewHolder holder, int position) {
        holder.imageView.setImageResource(discountedProductList.get(position).getProduct().getImageURL());
    }

    @Override
    public int getItemCount() {
        return discountedProductList.size();
    }

    public static class DiscountedProductViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.discountedProductImageView);
        }
    }
}
