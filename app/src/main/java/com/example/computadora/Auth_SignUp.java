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

public class Auth_SignUp extends AppCompatActivity {

    private TextView txt_redirect_to_Sign_in_SU, txt_redirect_to_PWRecover_SU;
    private TextInputEditText login_username_input_sign_up,login_password_input_sign_up, login_passwordRepeat_input_sign_up, login_Email_input_sign_up;
    private TextInputLayout username_input_layout_sign_up,password_input_layout_sign_up,passwordRepeate_input_layout_sign_up,Email_input_layout_sign_up;
    private Button btn_submit_Sign_up ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_sign_up);

        initValue();
        redirectText();
        handleSubmit();
    }

    private void handleErrDisplay() {
//        username_input_layout.setError("This is an error");
    }

    private void handleSubmit() {
        btn_submit_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printValue();
            }
        });
    }

    private void printValue() {
        System.out.println("username " + login_username_input_sign_up.getText().toString());
        System.out.println("password " + login_password_input_sign_up.getText().toString());
        System.out.println("pw repeat " + login_passwordRepeat_input_sign_up.getText().toString());
        System.out.println("email " + login_Email_input_sign_up.getText().toString());
    }


    private void redirectText() {
        txt_redirect_to_Sign_in_SU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_SignUp.this, Auth_Login.class);
            }
        });

        txt_redirect_to_PWRecover_SU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectListener(Auth_SignUp.this, Auth_PWRecover.class);
            }
        });
    }

    private void redirectListener(Context context, Class redirectTo) {
        Intent intent = new Intent(context,redirectTo);
        startActivity(intent);
    }

    private void initValue() {
//     txt
        txt_redirect_to_Sign_in_SU = findViewById(R.id.txt_redirect_to_Sign_In_PWR);
        txt_redirect_to_PWRecover_SU = findViewById(R.id.txt_redirect_to_Sign_Up_PWR);
//     input
        login_username_input_sign_up = findViewById(R.id.login_username_input_PWR);
        login_password_input_sign_up = findViewById(R.id.login_password_input_PWR);
        login_passwordRepeat_input_sign_up = findViewById(R.id.login_passwordRepeat_input_PWR);
        login_Email_input_sign_up = findViewById(R.id.login_Email_input_PWR);
//    layout
        username_input_layout_sign_up = findViewById(R.id.username_input_layout_PWR);
        password_input_layout_sign_up = findViewById(R.id.password_input_layout_PWR);
        passwordRepeate_input_layout_sign_up = findViewById(R.id.passwordRepeate_input_layout_PWR);
        Email_input_layout_sign_up = findViewById(R.id.Email_input_layout_PWR);
//    button
        btn_submit_Sign_up = findViewById(R.id.btn_submit_PWR);
    }
}