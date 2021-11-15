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

public class Auth_PWRecover extends AppCompatActivity {

    private TextView txt_redirect_to_Sign_In_PWR, txt_redirect_to_Sign_Up_PWR;
    private TextInputEditText login_username_input_PWR,login_password_input_PWR, login_passwordRepeat_input_PWR, login_Email_input_PWR;
    private TextInputLayout username_input_layout_PWR,password_input_layout_PWR,passwordRepeate_input_layout_PWR,Email_input_layout_PWR;
    private Button btn_submit_PWR ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_pwrecover);

        initValue();
        redirectText();
        handleSubmit();

    }

    private void handleErrDisplay() {
//        username_input_layout.setError("This is an error");
    }

    private void handleSubmit() {
        btn_submit_PWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printValue();
            }
        });
    }

    private void printValue() {
        System.out.println("username " + login_username_input_PWR.getText().toString());
        System.out.println("password " + login_password_input_PWR.getText().toString());
        System.out.println("pw repeat " + login_passwordRepeat_input_PWR.getText().toString());
        System.out.println("email " + login_Email_input_PWR.getText().toString());
    }


    private void redirectText() {
        txt_redirect_to_Sign_In_PWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_PWRecover.this, Auth_Login.class);
            }
        });

        txt_redirect_to_Sign_Up_PWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_PWRecover.this, Auth_SignUp.class);
            }
        });
    }

    private void redirectListener(Context context, Class redirectTo) {
        Intent intent = new Intent(context,redirectTo);
        startActivity(intent);
    }

    private void initValue() {
//     txt
        txt_redirect_to_Sign_In_PWR = findViewById(R.id.txt_redirect_to_Sign_In_PWR);
        txt_redirect_to_Sign_Up_PWR = findViewById(R.id.txt_redirect_to_Sign_Up_PWR);
//     input
        login_username_input_PWR = findViewById(R.id.login_username_input_PWR);
        login_password_input_PWR = findViewById(R.id.login_password_input_PWR);
        login_passwordRepeat_input_PWR = findViewById(R.id.login_passwordRepeat_input_PWR);
        login_Email_input_PWR = findViewById(R.id.login_Email_input_PWR);
//    layout
        username_input_layout_PWR = findViewById(R.id.username_input_layout_PWR);
        password_input_layout_PWR = findViewById(R.id.password_input_layout_PWR);
        passwordRepeate_input_layout_PWR = findViewById(R.id.passwordRepeate_input_layout_PWR);
        Email_input_layout_PWR = findViewById(R.id.Email_input_layout_PWR);
//    button
        btn_submit_PWR = findViewById(R.id.btn_submit_PWR);
    }
}