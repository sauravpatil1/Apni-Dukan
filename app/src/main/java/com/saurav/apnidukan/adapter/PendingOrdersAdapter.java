package com.saurav.apnidukan.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.saurav.apnidukan.OrderDetailsActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Order;

import java.util.List;

public class PendingOrdersAdapter extends RecyclerView.Adapter<PendingOrdersAdapter.PendingOrdersViewHolder> {
    Context context;
    List<Order> orderList;

    public PendingOrdersAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public PendingOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pending_orders_rt, parent, false);
        return new PendingOrdersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingOrdersAdapter.PendingOrdersViewHolder holder, int position) {
        holder.name.setText(orderList.get(position).getCustomer().getName());
        holder.address.setText(orderList.get(position).getCustomer().getAddress());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                context.startActivity(intent);
            }
        });
        holder.shipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.shipButton.setText("Shipped");
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public static class PendingOrdersViewHolder extends RecyclerView.ViewHolder{
        TextView name, address;
        Button shipButton;
        public PendingOrdersViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.customerNameTextView);
            address = itemView.findViewById(R.id.customerAddressTextView);
            shipButton = itemView.findViewById(R.id.shipButton);
        }
    }
}
