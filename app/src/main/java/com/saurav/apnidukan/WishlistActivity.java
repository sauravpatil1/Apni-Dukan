package com.saurav.apnidukan;


import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saurav.apnidukan.adapter.WishListProductAdapter;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class WishlistActivity extends AppCompatActivity {
    RecyclerView wishListRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        wishListRecyclerView = findViewById(R.id.wishlistRecyclerView);
        List<Product> wishListProductList = new ArrayList<>();
        wishListProductList.add(new Product(R.drawable.discounted, 45, "Red apples", "A good quality apple from kashmir"));
        wishListProductList.add(new Product(R.drawable.discounted1, 45, "Red apples", "A good quality apple from kashmir"));
        wishListProductList.add(new Product(R.drawable.discounted4, 45, "Red apples", "A good quality apple from kashmir"));
        wishListProductList.add(new Product(R.drawable.discounted3, 45, "Red apples", "A good quality apple from kashmir"));
        wishListProductList.add(new Product(R.drawable.discounted2, 45, "Red apples", "A good quality apple from kashmir"));
        wishListProductList.add(new Product(R.drawable.discounted, 45, "Red apples", "A good quality apple from kashmir"));

        setWishListRecyclerView(wishListProductList);
    }

    public void setWishListRecyclerView(List<Product> wishListProductList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        wishListRecyclerView.setLayoutManager(layoutManager);
        wishListRecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(16), true));
        wishListRecyclerView.setItemAnimator(new DefaultItemAnimator());
        WishListProductAdapter adapter= new WishListProductAdapter(this, wishListProductList);
        wishListRecyclerView.setAdapter(adapter);
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}