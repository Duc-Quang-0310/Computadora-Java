package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Account_ChangeInfo extends AppCompatActivity {

    private TextInputLayout inputCity_layout, inputDistrict_layout, inputSubDistrict_layout;
    private TextInputEditText inputCity, inputDistrict, inputSubDistrict;
    private Button submitChange_Info;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_info);

        initValue();
        submit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitChange_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

    }

    private void submit() {
        System.out.println("input city" + inputCity.getText());
        System.out.println("inputDistrict" + inputDistrict.getText());
        System.out.println("inputSubDistrict " + inputSubDistrict.getText());
    }


    private void initValue() {
        inputCity_layout = findViewById(R.id.inputCity_layout);
        inputDistrict_layout = findViewById(R.id.inputDistrict_layout);
        inputSubDistrict_layout = findViewById(R.id.inputSubDistrict_layout);

        inputCity = findViewById(R.id.inputCity);
        inputDistrict = findViewById(R.id.inputDistrict);
        inputSubDistrict = findViewById(R.id.inputSubDistrict);

        submitChange_Info = findViewById(R.id.submitChange_Info);

        imageView = findViewById(R.id.previousPage);
    }

    private void redirector(Class destination){
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}