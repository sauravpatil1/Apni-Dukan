package com.saurav.apnidukan.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.MainActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.LocalShopAdapter;
import com.saurav.apnidukan.helper.DistanceFinder;
import com.saurav.apnidukan.model.Shop;

import java.util.ArrayList;
import java.util.List;

public class CategoryByLocationFragment extends Fragment {
    RecyclerView recyclerView;
    private LocalShopAdapter adapter;
    List<Shop> shopList;

    private DatabaseReference reference;

    public CategoryByLocationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_category_by_location, container, false);
        recyclerView = view.findViewById(R.id.shopInfoRecyclerView);
        List<Shop> shopList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new LocalShopAdapter(getContext(), shopList);
        recyclerView.setAdapter(adapter);
        reference = FirebaseDatabase.getInstance().getReference("shop");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Shop shop = dataSnapshot.getValue(Shop.class);
                    if(DistanceFinder.distance(shop.getLatitude(), shop.getLongitude(), MainActivity.userCurrentLocation.getLatitude(), MainActivity.userCurrentLocation.getLongitude()) <= 10)
                    shopList.add(shop);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }
}