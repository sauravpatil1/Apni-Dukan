package com.saurav.apnidukan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.adapter.SearchProductAdapter;
import com.saurav.apnidukan.helper.DistanceFinder;
import com.saurav.apnidukan.model.Product;
import com.saurav.apnidukan.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private SearchProductAdapter adapter;
    List<Product> productList;
    List<Shop> nearShopList;

    private DatabaseReference dbRefShop;
    String lastSearch = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.productListRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));

        productList = new ArrayList<>();
        nearShopList = new ArrayList<>();

        adapter = new SearchProductAdapter(SearchActivity.this, productList);
        recyclerView.setAdapter(adapter);
    }

    public void onClickSearch(View view){
        EditText editText = findViewById(R.id.sSearchEditText);
        String searchTerm = editText.getText().toString();
        if(searchTerm.trim().equals(lastSearch))return;
        lastSearch = searchTerm;

        dbRefShop = FirebaseDatabase.getInstance().getReference("shop");
        dbRefShop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Shop> shopList = new ArrayList<>();

                for (DataSnapshot postSnapShot : snapshot.getChildren()){
                    Shop shop = postSnapShot.getValue(Shop.class);
                    shopList.add(shop);
                }

                for (int i=0;i<shopList.size();i++){
                    Shop shop = shopList.get(i);
                    try {
                        if (DistanceFinder.distance(shop.getLatitude(), shop.getLongitude(), MainActivity.userCurrentLocation.getLatitude(), MainActivity.userCurrentLocation.getLongitude()) <= 10) {
                            nearShopList.add(shop);
                        }
                    }catch (Exception e){
                        Toast.makeText(SearchActivity.this, "Enable location", Toast.LENGTH_LONG).show();
                    }
                }

                for(int i=0;i<nearShopList.size();i++) {
                    Query query = FirebaseDatabase.getInstance().getReference("product").orderByChild("shopId").equalTo(nearShopList.get(i).id);
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //productList.removeAll(productList);
                            for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                                Product product = dataSnapshot.getValue(Product.class);
                                if(product.getBrandName().equalsIgnoreCase(searchTerm) || product.getType().equalsIgnoreCase(searchTerm)) {
                                    productList.add(product);
                                }
                            }
                            adapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(SearchActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SearchActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}