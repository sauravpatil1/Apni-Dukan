package com.saurav.apnidukan.fragment;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.saurav.apnidukan.GridSpacingItemDecoration;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.AllCategoryAdapter;
import com.saurav.apnidukan.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment {
    RecyclerView allCategoryRecyclerView;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        allCategoryRecyclerView = view.findViewById(R.id.allCategoryRecyclerView);
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(R.drawable.category1, "can food"));
        categoryList.add(new Category(R.drawable.category2, "Milk"));
        categoryList.add(new Category(R.drawable.category3, "Fruits"));
        categoryList.add(new Category(R.drawable.category4, "Vegetables"));
        categoryList.add(new Category(R.drawable.category5, "Alcohol"));
        categoryList.add(new Category(R.drawable.category6, "meat"));
        categoryList.add(new Category(R.drawable.category7, "Packed food"));
        categoryList.add(new Category(R.drawable.category8, "Toiletries"));
        categoryList.add(new Category(R.drawable.category1, "can food"));

        setAllCategoryRecyclerView(categoryList);
        return view;
    }
    public void setAllCategoryRecyclerView(List<Category> categoryList){
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        allCategoryRecyclerView.setLayoutManager(layoutManager);
        allCategoryRecyclerView.addItemDecoration(new GridSpacingItemDecoration(3, dpToPx(16), true));
        allCategoryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        AllCategoryAdapter allCategoryAdapter = new AllCategoryAdapter(getContext(), categoryList);
        allCategoryRecyclerView.setAdapter(allCategoryAdapter);
    }
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}