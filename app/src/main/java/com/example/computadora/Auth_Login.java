package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Auth_Login extends AppCompatActivity {



    private TextView txt_Redirect_to_Sign_Up, txt_Redirect_to_PWRecover;
    private TextInputEditText login_username_input,login_password_input;
    private TextInputLayout username_input_layout,pasword_input_layout;
    private Button btn_Login ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_login);

        initValue();
        redirectText();
        handleSubmit();

        handleErrDisplay();

        SupportClass.setTimeout(() -> redirecter(), 2000);
    }

    private void redirecter() {
        Intent intent = new Intent(Auth_Login.this,Home.class);
        startActivity(intent);
    }

    private void handleErrDisplay() {
//        username_input_layout.setError("This is an error");
    }

    private void handleSubmit() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printValue();
            }
        });
    }

    private void printValue() {
        System.out.println("username " + login_username_input.getText().toString());
        System.out.println("password " + login_password_input.getText().toString());
    }


    private void redirectText() {
        txt_Redirect_to_Sign_Up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_Login.this, Auth_SignUp.class);
            }
        });

        txt_Redirect_to_PWRecover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_Login.this, Auth_PWRecover.class);
            }
        });
    }

    private void redirectListener(Context context, Class redirectTo) {
        Intent intent = new Intent(context,redirectTo);
        startActivity(intent);
    }

    private void initValue() {
        txt_Redirect_to_Sign_Up = findViewById(R.id.txt_Redirect_to_Sign_Up);
        txt_Redirect_to_PWRecover = findViewById(R.id.txt_Redirect_to_PWRecover);
        login_username_input = findViewById(R.id.login_username_input);
        login_password_input = findViewById(R.id.login_password_input);
        username_input_layout = findViewById(R.id.username_input_layout);
        pasword_input_layout = findViewById(R.id.pasword_input_layout);
        btn_Login = findViewById(R.id.btn_Login);
    }
}