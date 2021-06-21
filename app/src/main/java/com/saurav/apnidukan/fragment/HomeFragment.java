package com.saurav.apnidukan.fragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saurav.apnidukan.GridSpacingItemDecoration;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.CategoryAdapter;
import com.saurav.apnidukan.adapter.DiscountedProductAdapter;
import com.saurav.apnidukan.adapter.RecentlyViewedProductAdapter;
import com.saurav.apnidukan.model.Category;
import com.saurav.apnidukan.model.DiscountedProduct;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    RecyclerView discountedRecyclerView, categoryRecyclerView, recentlyViewedRecyclerView;
    public HomeFragment(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        discountedRecyclerView = view.findViewById(R.id.discountedRecycler);
        categoryRecyclerView = view.findViewById(R.id.categoryRecyclerView);
        recentlyViewedRecyclerView = view.findViewById(R.id.recentlyViewedRecyclerView);

        List<DiscountedProduct> discountedProductList= new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();
        List<Product> recentlyViewedProductList = new ArrayList<>();

        discountedProductList.add(new DiscountedProduct(new Product(R.drawable.discounted)));
        discountedProductList.add(new DiscountedProduct(new Product(R.drawable.discounted1)));
        discountedProductList.add(new DiscountedProduct(new Product(R.drawable.discounted2)));
        discountedProductList.add(new DiscountedProduct(new Product(R.drawable.discounted3)));
        discountedProductList.add(new DiscountedProduct(new Product(R.drawable.discounted4)));

        categoryList.add(new Category(1, R.drawable.category1));
        categoryList.add(new Category(1, R.drawable.category2));
        categoryList.add(new Category(1, R.drawable.category3));
        categoryList.add(new Category(1, R.drawable.category4));
        categoryList.add(new Category(1, R.drawable.category5));
        categoryList.add(new Category(1, R.drawable.category6));

        recentlyViewedProductList.add(new Product(R.drawable.discounted, 45, "Red apples", "A good quality apple from kashmir"));
        recentlyViewedProductList.add(new Product(R.drawable.discounted1, 45, "Red apples", "A good quality apple from kashmir"));
        recentlyViewedProductList.add(new Product(R.drawable.discounted4, 45, "Red apples", "A good quality apple from kashmir"));
        recentlyViewedProductList.add(new Product(R.drawable.discounted3, 45, "Red apples", "A good quality apple from kashmir"));
        recentlyViewedProductList.add(new Product(R.drawable.discounted2, 45, "Red apples", "A good quality apple from kashmir"));
        recentlyViewedProductList.add(new Product(R.drawable.discounted, 45, "Red apples", "A good quality apple from kashmir"));

        setDiscountedRecyclerView(discountedProductList);
        setCategoryRecyclerView(categoryList);
        setRecentlyViewedRecyclerView(recentlyViewedProductList);

        return view;
    }
    public void setDiscountedRecyclerView(List<DiscountedProduct> discountedProductList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        discountedRecyclerView.setLayoutManager(manager);
        DiscountedProductAdapter adapter = new DiscountedProductAdapter(getContext(), discountedProductList);
        discountedRecyclerView.setAdapter(adapter);
    }
    public void setCategoryRecyclerView(List<Category> categoryList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(manager);
        CategoryAdapter adapter = new CategoryAdapter(getContext(), categoryList);
        categoryRecyclerView.setAdapter(adapter);
    }

    public void setRecentlyViewedRecyclerView(List<Product> productList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        recentlyViewedRecyclerView.setLayoutManager(manager);
        RecentlyViewedProductAdapter adapter = new RecentlyViewedProductAdapter(getContext(), productList);
        recentlyViewedRecyclerView.setAdapter(adapter);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}