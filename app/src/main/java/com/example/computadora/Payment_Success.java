package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Payment_Success extends AppCompatActivity {

    private Button buttonToProduct;
    FragmentManager manager = getFragmentManager();
    FragmentTransaction transaction = manager.beginTransaction();
    private static final int PRODUCT_FRAGMENT = 1000367;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_success);

        buttonToProduct = findViewById(R.id.buttonToProduct);

        buttonToProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Payment_Success.this, Home.class);
                intent.putExtra("view", PRODUCT_FRAGMENT);
                startActivity(intent);
            }
        });
    }
}