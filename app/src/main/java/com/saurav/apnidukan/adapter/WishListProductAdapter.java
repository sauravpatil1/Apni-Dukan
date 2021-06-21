package com.saurav.apnidukan.adapter;


import android.content.Context;
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

public class WishListProductAdapter extends RecyclerView.Adapter<WishListProductAdapter.WishListProductViewHolder> {
    Context context;
    List<Product> wishListProductList;

    public WishListProductAdapter(Context context, List<Product> wishListProductList) {
        this.context = context;
        this.wishListProductList = wishListProductList;
    }

    @NonNull
    @Override
    public WishListProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wishlist_product_row_item, parent, false);
        return new WishListProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  WishListProductViewHolder holder, int position) {
        holder.productImage.setImageResource(wishListProductList.get(position).getImageURL());
        holder.name.setText(wishListProductList.get(position).getBrandName());
        holder.price.setText(Integer.toString(wishListProductList.get(position).getPrice()));
        holder.moveToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });
        holder.close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo
            }
        });
    }

    @Override
    public int getItemCount() {
        return wishListProductList.size();
    }

    public static class WishListProductViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage, close;
        TextView name, price, moveToCart;
        public WishListProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.wishlistProductImageView);
            close = itemView.findViewById(R.id.closeImageView);
            name = itemView.findViewById(R.id.wishlistProductNameTextView);
            price = itemView.findViewById(R.id.WishListProductPrice);
            moveToCart = itemView.findViewById(R.id.whishlistMoveToCartTextView);
        }
    }
}
