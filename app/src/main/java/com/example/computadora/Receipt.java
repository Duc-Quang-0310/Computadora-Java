package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Receipt extends AppCompatActivity {

    private TextView txtName, txtAddress, txtPhone, txtMoney, txtChangePaymentInfo  ;
    private Button submit_payment;
    private ImageView back_payment_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        initValue();

        setValue();
    }

    private void initValue () {
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtMoney = findViewById(R.id.txtMoney);
        txtChangePaymentInfo = findViewById(R.id.txtChangePaymentInfo);
        submit_payment = findViewById(R.id.submit_payment);
        back_payment_image = findViewById(R.id.back_payment_image);

        back_payment_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtChangePaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Receipt.this, Account_ChangeInfo.class);
                startActivity(intent);
            }
        });

        submit_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Receipt.this, Payment_Success.class);
                startActivity(intent);
            }
        });


    }

    private void setValue () {
        //TODO: set value for txtName, txtAddress, txtPhone, txtMoney, txtChangePaymentInfo;

    }
}