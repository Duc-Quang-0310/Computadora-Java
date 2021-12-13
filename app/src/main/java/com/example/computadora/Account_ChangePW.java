package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Account_ChangePW extends AppCompatActivity {

    private TextInputLayout inputPW_layout, inputNewPW_layout, inputNewPWRepeat_layout;
    private TextInputEditText inputPW, inputNewPW, inputNewPWRepeat;
    private Button submitChange_PW;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_pw);

        initValue();
        submit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitChange_PW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
    }

    private void submit() {
        System.out.println("input PW" + inputPW.getText());
        System.out.println("input NEW PW" + inputNewPW.getText());
        System.out.println("input NEW PW REPEAT " + inputNewPWRepeat.getText());
    }


    private void initValue() {
        inputPW_layout = findViewById(R.id.inputPW_layout);
        inputNewPW_layout = findViewById(R.id.inputNewPW_layout);
        inputNewPWRepeat_layout = findViewById(R.id.inputNewPWRepeat_layout);

        inputPW = findViewById(R.id.inputPW);
        inputNewPW = findViewById(R.id.inputNewPW);
        inputNewPWRepeat = findViewById(R.id.inputNewPWRepeat);

        submitChange_PW = findViewById(R.id.submitChange_PW);

        imageView = findViewById(R.id.previousPage_changePW);
    }

    private void redirector(Class destination){
        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }


}