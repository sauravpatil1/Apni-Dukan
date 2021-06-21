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

public class ShoppingCartProductAdapter extends RecyclerView.Adapter<ShoppingCartProductAdapter.ShoppingCartProductViewHolder> {
    Context context;
    List<Product> productList;

    public ShoppingCartProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ShoppingCartProductViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_rt, parent, false);
        return new ShoppingCartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartProductViewHolder holder, int position) {
        holder.productImage.setImageResource(productList.get(position).getImageURL());
        holder.name.setText(productList.get(position).getBrandName());
        holder.price.setText(Integer.toString(productList.get(position).getPrice()));
        holder.description.setText(productList.get(position).getDescription());
        holder.shop.setText(productList.get(position).getShop().getName());
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ShoppingCartProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView name, description, price, shop, remove, moveWishList;
        public ShoppingCartProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.orderedProductImageView);
            name = itemView.findViewById(R.id.orderBrandName);
            price = itemView.findViewById(R.id.orderPrice);
            shop = itemView.findViewById(R.id.orderedShop);
            description = itemView.findViewById(R.id.orderDescriptionTextView);
            remove = itemView.findViewById(R.id.cancelTextView);
            moveWishList = itemView.findViewById(R.id.orderedStatus);
        }
    }
}
