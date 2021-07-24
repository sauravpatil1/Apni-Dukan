package com.saurav.apnidukan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
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
import com.saurav.apnidukan.adapter.ShoppingCartProductAdapter;
import com.saurav.apnidukan.model.Cart;
import com.saurav.apnidukan.model.Order;
import com.saurav.apnidukan.model.Product;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartActivity extends AppCompatActivity {
    RecyclerView cartProductRecyclerView;
    TextView addressTextView, totalAmount;
    List<Product> productList;
    List<Cart> cartList;
    DatabaseReference dbRef;
    ShoppingCartProductAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        cartProductRecyclerView = findViewById(R.id.cartProductRecyclerView);
        addressTextView = findViewById(R.id.shoppingCartUserAddressTextView);
        totalAmount = findViewById(R.id.totalAmountTextView);

        addressTextView.setText("Deliver to : "+MainActivity.currentUser.getAddress());
        productList = new ArrayList<>();
        cartList = new ArrayList<>();
        cartProductRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ShoppingCartProductAdapter(this, productList, cartList);
        cartProductRecyclerView.setAdapter(adapter);

        dbRef = FirebaseDatabase.getInstance().getReference("cart");
        dbRef.orderByChild("userId").equalTo(MainActivity.currentUser.id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> productIdList = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Cart cart = ds.getValue(Cart.class);
                    cartList.add(cart);
                    productIdList.add(cart.getProductId());
                }
                productList.clear();
                for(int i=0;i<productIdList.size();i++){
                    Query productRef = FirebaseDatabase.getInstance().getReference("product").orderByChild("productId").equalTo(productIdList.get(i));
                    productRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int calTotalAmount = 0;
                            for(DataSnapshot ds : snapshot.getChildren()){
                                Product product = ds.getValue(Product.class);
                                productList.add(product);
                                calTotalAmount = calTotalAmount + product.getPrice();
                            }
                            totalAmount.setText(Integer.toString(calTotalAmount));
                            adapter.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(ShoppingCartActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ShoppingCartActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClickCallChangeAddress(View view){
        Intent intent = new Intent(ShoppingCartActivity.this, ChangeAddressActivity.class);
        startActivity(intent);
    }

    public void onClickPlaceOrder(View view){
        DatabaseReference orderRef = FirebaseDatabase.getInstance().getReference("order");
        for(int i=0;i<productList.size();i++){
            String id = orderRef.push().getKey();
            Order order = new Order(id, MainActivity.currentUser.id, productList.get(i).getProductId(), productList.get(i).getShopId(), "placed", MainActivity.currentUser.getName(), MainActivity.currentUser.getAddress());
            orderRef.child(id).setValue(order);
        }
        for(int i=0;i<cartList.size();i++){
            DatabaseReference removeCart = FirebaseDatabase.getInstance().getReference("cart").child(cartList.get(i).getId());
            removeCart.removeValue();
        }
        Intent intent = new Intent(ShoppingCartActivity.this, SuccessfulActivity.class);
        startActivity(intent);
    }

}