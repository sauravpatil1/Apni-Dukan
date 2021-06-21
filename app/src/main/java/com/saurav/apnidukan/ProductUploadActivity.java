package com.saurav.apnidukan;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.saurav.apnidukan.databinding.ActivityProductUploadBinding;
import com.saurav.apnidukan.model.Product;

public class ProductUploadActivity extends AppCompatActivity {
    ActivityProductUploadBinding binding;
    String [] categoryArray, typeArray;
    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        storageReference = FirebaseStorage.getInstance().getReference("productImage");
        databaseReference = FirebaseDatabase.getInstance().getReference("product");

        categoryArray = new String[]{"Food", "Drink", "Oil", "Dal", "Crockery"};
        typeArray = new String[]{"Food", "Oil", "Toor", "Chaval"};

        ArrayAdapter<String> categorySpinnerAdapter= new ArrayAdapter<String>(this, R.layout.custom_spinner_layout, categoryArray);
        ArrayAdapter<String> typeSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_layout, typeArray);
        binding.categorySpinner.setAdapter(categorySpinnerAdapter);
        binding.typeSpinner.setAdapter(typeSpinnerAdapter);

        binding.chooseImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });
        binding.uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadImage();
            }
        });
    }

    private String getFileExtension(Uri uri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
    }

    private void uploadImage() {
        if(imageUri != null){
            StorageReference fileReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(imageUri));
            fileReference.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(ProductUploadActivity.this, "Upload Successful", Toast.LENGTH_LONG).show();
                            fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    Product product = new Product(uri.toString() , Integer.parseInt(binding.price.getText().toString()),
                                            Integer.parseInt(binding.discount.getText().toString()), binding.brandName.getText().toString(), binding.typeSpinner.getSelectedItem().toString(),
                                            binding.weight.getText().toString(), binding.description.getText().toString());
                                    String productId = databaseReference.push().getKey();
                                    databaseReference.child(productId).setValue(product);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(ProductUploadActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
        }else {
            Toast.makeText(ProductUploadActivity.this, "Pick Image", Toast.LENGTH_LONG).show();
        }
    }

    private void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK &&
            data != null && data.getData() != null){
            imageUri = data.getData();
            binding.choosenImageView.setImageURI(imageUri);
        }
    }
}