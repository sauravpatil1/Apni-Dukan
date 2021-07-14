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

import com.saurav.apnidukan.CategoryWiseProductActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.model.Category;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.AllCategoryViewHolder> {
    Context context;
    List<Category> categoryList;

    public AllCategoryAdapter(Context context, List<Category> allCategoryList) {
        this.context = context;
        this.categoryList = allCategoryList;
    }

    @NonNull
    @Override
    public AllCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_all_category_rt, parent, false);
        return new AllCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.AllCategoryViewHolder holder, int position) {
        holder.allCategoryImageView.setImageResource(categoryList.get(position).getImageURL());
        holder.name.setText(categoryList.get(position).getType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CategoryWiseProductActivity.class);
                intent.putExtra("TYPE", categoryList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public static class AllCategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView allCategoryImageView;
        TextView name;
        public AllCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            allCategoryImageView = itemView.findViewById(R.id.allCategoryImageView);
            name = itemView.findViewById(R.id.categoryNameTextView);
        }
    }
}
