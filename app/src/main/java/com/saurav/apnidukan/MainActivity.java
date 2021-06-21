package com.saurav.apnidukan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.saurav.apnidukan.databinding.ActivityMainBinding;
import com.saurav.apnidukan.fragment.CategoryByLocationFragment;
import com.saurav.apnidukan.fragment.CategoryFragment;
import com.saurav.apnidukan.fragment.HomeFragment;
import com.saurav.apnidukan.fragment.ProfileFragment;
import com.saurav.apnidukan.fragment.ShopOwnerFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        navigationView = findViewById(R.id.bottomNav);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, new HomeFragment());
        fragmentTransaction.commit();
        binding.searchImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
        binding.cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShoppingCartActivity.class);
                startActivity(intent);
            }
        });
        binding.wishlistImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WishlistActivity.class);
                startActivity(intent);
            }
        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (item.getItemId()){
                    case R.id.home:
                        fragmentTransaction.replace(R.id.container, new HomeFragment());
                        break;
                    case R.id.category:
                        fragmentTransaction.replace(R.id.container, new CategoryFragment());
                        break;
                    case R.id.entrepreneur:
                        fragmentTransaction.replace(R.id.container, new ShopOwnerFragment());
                        break;
                    case R.id.localShop:
                        fragmentTransaction.replace(R.id.container, new CategoryByLocationFragment());
                        break;
                    case R.id.profile:
                        fragmentTransaction.replace(R.id.container, new ProfileFragment());
                        break;
                    default:
                        return false;
                }
                fragmentTransaction.commit();
                return true;
            }
        });
    }
}