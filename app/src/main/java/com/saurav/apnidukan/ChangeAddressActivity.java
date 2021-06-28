package com.saurav.apnidukan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChangeAddressActivity extends AppCompatActivity {
    EditText address;
    Button button;
    TextView currentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);

        address = findViewById(R.id.enterAddressEditText);
        button = findViewById(R.id.setAddressButton);
        currentAddress = findViewById(R.id.currentAddressTextView);

        currentAddress.setText("Current address : " + MainActivity.currentUser.getAddress());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(MainActivity.currentUser.getId()).child("address").setValue(address.getText().toString());
            }
        });

    }
}