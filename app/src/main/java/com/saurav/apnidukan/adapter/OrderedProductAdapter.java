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

import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Product;

import java.util.List;

public class OrderedProductAdapter extends RecyclerView.Adapter<OrderedProductAdapter.OrderedProductViewHolder> {
    Context context;
    List<Product> productList;

    public OrderedProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public OrderedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ordered_product_rt, parent, false);
        return new OrderedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderedProductAdapter.OrderedProductViewHolder holder, int position) {
        holder.productImage.setImageResource(productList.get(position).getImageURL());
        holder.description.setText(productList.get(position).getDescription());
        holder.brandName.setText(productList.get(position).getBrandName());
        holder.packedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.packedButton.setText("PACKED");
                //todo set color
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class OrderedProductViewHolder extends RecyclerView.ViewHolder{
        ImageView productImage;
        Button packedButton;
        TextView brandName,description, quantity;
        public OrderedProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.orderedProductAdapterImageView);
            brandName = itemView.findViewById(R.id.orderedProductBrandName);
            description = itemView.findViewById(R.id.ordered_prodcut_description);
            quantity = itemView.findViewById(R.id.quantity);
            packedButton = itemView.findViewById(R.id.packedButton);
        }
    }
}
