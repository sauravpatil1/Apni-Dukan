package com.saurav.apnidukan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Cart;
import com.saurav.apnidukan.model.Product;

import java.util.List;

public class ShoppingCartProductAdapter extends RecyclerView.Adapter<ShoppingCartProductAdapter.ShoppingCartProductViewHolder> {
    Context context;
    List<Product> productList;
    List<Cart> cartList;

    public ShoppingCartProductAdapter(Context context, List<Product> productList, List<Cart> cartList) {
        this.context = context;
        this.productList = productList;
        this.cartList = cartList;
    }

    @NonNull
    @Override
    public ShoppingCartProductViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_rt, parent, false);
        return new ShoppingCartProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingCartProductViewHolder holder, int position) {
        holder.name.setText(productList.get(position).getBrandName());
        holder.price.setText(Integer.toString(productList.get(position).getPrice()));
        holder.description.setText(productList.get(position).getDescription());
        holder.shop.setText(productList.get(position).getShopName());
        holder.weight.setText(productList.get(position).getWeight());
        Glide.with(context).load(productList.get(position).getImage()).into(holder.productImage);
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("cart").child(cartList.get(position).getId());
                dbRef.removeValue();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ShoppingCartProductViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView name, description, price, shop, remove, moveWishList, weight;
        public ShoppingCartProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.scProductImageView);
            name = itemView.findViewById(R.id.scBrandName);
            price = itemView.findViewById(R.id.scProductPrice);
            shop = itemView.findViewById(R.id.scShopName);
            description = itemView.findViewById(R.id.scDescription);
            remove = itemView.findViewById(R.id.scRemoveTextView);
            moveWishList = itemView.findViewById(R.id.orderedStatus);
            weight = itemView.findViewById(R.id.scWeightTextView);

        }
    }
}
