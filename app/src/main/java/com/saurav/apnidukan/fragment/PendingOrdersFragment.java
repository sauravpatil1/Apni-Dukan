package com.saurav.apnidukan.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.adapter.PendingOrdersAdapter;
import com.saurav.apnidukan.model.Customer;
import com.saurav.apnidukan.model.Order;

import java.util.ArrayList;
import java.util.List;

public class PendingOrdersFragment extends Fragment {
    RecyclerView productDisplayRecyclerView;

    public PendingOrdersFragment() {
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
        View view = inflater.inflate(R.layout.fragment_pending_orders, container, false);
        productDisplayRecyclerView = view.findViewById(R.id.orderRecyclerView);
        //todo create list
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(new Customer("Saurav Patil","Bus stand bodwad")));
        orderList.add(new Order(new Customer("Rushikesh Patil","sakla colony bodawad")));
        orderList.add(new Order(new Customer("Arayan Patil", "Sharada colony bodwad")));
        orderList.add(new Order(new Customer("Nikhil Shevalkar","Kurha Bhusawal")));
        orderList.add(new Order(new Customer("Gaurav Patil","anturli Pachora")));
        orderList.add(new Order(new Customer("Saurav Patil","Bus stand bodwad")));
        orderList.add(new Order(new Customer("Rushikesh Patil","sakla colony bodawad")));
        orderList.add(new Order(new Customer("Arayan Patil", "Sharada colony bodwad")));
        orderList.add(new Order(new Customer("Nikhil Shevalkar","Kurha Bhusawal")));
        orderList.add(new Order(new Customer("Gaurav Patil","anturli Pachora")));
        orderList.add(new Order(new Customer("Saurav Patil","Bus stand bodwad")));
        orderList.add(new Order(new Customer("Rushikesh Patil","sakla colony bodawad")));
        orderList.add(new Order(new Customer("Arayan Patil", "Sharada colony bodwad")));
        orderList.add(new Order(new Customer("Nikhil Shevalkar","Kurha Bhusawal")));
        orderList.add(new Order(new Customer("Gaurav Patil","anturli Pachora")));
        orderList.add(new Order(new Customer("Saurav Patil","Bus stand bodwad")));
        orderList.add(new Order(new Customer("Rushikesh Patil","sakla colony bodawad")));
        orderList.add(new Order(new Customer("Arayan Patil", "Sharada colony bodwad")));
        orderList.add(new Order(new Customer("Nikhil Shevalkar","Kurha Bhusawal")));
        orderList.add(new Order(new Customer("Gaurav Patil","anturli Pachora")));
        setProductDisplayRecyclerView(orderList);
        return view;
    }
    public void setProductDisplayRecyclerView(List<Order> orderList){
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false);
        productDisplayRecyclerView.setLayoutManager(manager);
        PendingOrdersAdapter adapter = new PendingOrdersAdapter(getContext(), orderList);
        productDisplayRecyclerView.setAdapter(adapter);
    }
}