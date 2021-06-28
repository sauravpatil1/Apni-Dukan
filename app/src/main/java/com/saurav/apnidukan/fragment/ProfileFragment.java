package com.saurav.apnidukan.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.saurav.apnidukan.ChangeAddressActivity;
import com.saurav.apnidukan.ChangePasswordActivity;
import com.saurav.apnidukan.MainActivity;
import com.saurav.apnidukan.MyOrdersActivity;
import com.saurav.apnidukan.ProductUploadActivity;
import com.saurav.apnidukan.R;
import com.saurav.apnidukan.SignInActivity;
import com.saurav.apnidukan.UpgradeShopKeeperActivity;
import com.saurav.apnidukan.adapter.OrderedProductStatusAdapter;
import com.saurav.apnidukan.model.User;


public class ProfileFragment extends Fragment {
    Button orders, address, upgradeShopkeeper, changePassword, logOut, upload;
    ImageView userImage;
    TextView userName;
    FirebaseAuth auth;
    DatabaseReference dbRef;

    public ProfileFragment() {
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        auth = FirebaseAuth.getInstance();
        //dbRef = FirebaseDatabase.getInstance().getReference();
        orders = view.findViewById(R.id.ordersButton);
        logOut = view.findViewById(R.id.logOutButton);
        upload = view.findViewById(R.id.uploadProductButton);
        upgradeShopkeeper = view.findViewById(R.id.upgradeShopKeeperButton);
        userName = view.findViewById(R.id.profileFragmentUserName);
        orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MyOrdersActivity.class);
                startActivity(intent);
            }
        });
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), SignInActivity.class);
                startActivity(intent);
            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.currentUser.isShopKeeper) {
                    Intent intent = new Intent(getContext(), ProductUploadActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Register shop first!",Toast.LENGTH_LONG).show();
                }
            }
        });

        upgradeShopkeeper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.currentUser.isShopKeeper) {
                    Intent intent = new Intent(getContext(), UpgradeShopKeeperActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "You are already a shopKeeper", Toast.LENGTH_LONG).show();
                }
            }
        });

        address = view.findViewById(R.id.addressButton);
        changePassword = view.findViewById(R.id.changePasswordButton);
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangeAddressActivity.class);
                startActivity(intent);
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        userName.setText(MainActivity.currentUser.name);
        return view;
    }
}