package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Auth_SignUp extends AppCompatActivity {
    private final static String SIGN_UP_URL = "https://backend-mobile-quang.herokuapp.com/api/user/sendUserInfoToRegisterDB";
    private TextView txt_redirect_to_Sign_in_SU, txt_redirect_to_PWRecover_SU;
    private TextInputEditText login_username_input_sign_up,login_password_input_sign_up, login_passwordRepeat_input_sign_up, login_Email_input_sign_up;
    private TextInputLayout username_input_layout_sign_up,password_input_layout_sign_up,passwordRepeate_input_layout_sign_up,Email_input_layout_sign_up;
    private Button btn_submit_Sign_up ;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_sign_up);

        initValue();

        redirectText();

        handleSubmit();
    }

    private void handleSubmit() {
        btn_submit_Sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_username_input_sign_up.getText().toString().trim();
                String password = login_password_input_sign_up.getText().toString().trim();
                String pwConfirm = login_passwordRepeat_input_sign_up.getText().toString().trim();
                String email = login_Email_input_sign_up.getText().toString().trim();
                boolean ok = verify(username , password , pwConfirm, email );
                if( ok ) {
                    Map<String, String> body = new HashMap<>();
                    body.put("username",username);
                    body.put("password",password);
                    body.put("email",email);
                    JSONObject parameters = new JSONObject(body);
                    register(parameters);
                }
            }
        });
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
//      volley
        requestQueue = Volley.newRequestQueue(this);

        //handle onClick
    }

    private void register(JSONObject parameters) {
        System.out.println("signing up");
        String url = SIGN_UP_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    if (success == true) {
                        String message = response.getString("message");
                        Toast.makeText(Auth_SignUp.this, message, Toast.LENGTH_LONG).show();
                        redirectListener(Auth_SignUp.this, Auth_Login.class);
                    } else {
                        String message = response.getString("message");
                        username_input_layout_sign_up.setError(message);
                        password_input_layout_sign_up.setError(message);
                        passwordRepeate_input_layout_sign_up.setError(message);
                        Email_input_layout_sign_up.setError(message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private boolean verify (String username, String password, String passwordConfirm, String email) {
        boolean result = true;
        if( username.equals("") || password.equals("") || passwordConfirm.equals("") || email.equals("")   ){
            username_input_layout_sign_up.setError("Các trường không được để trống");
            password_input_layout_sign_up.setError("Các trường không được để trống");
            passwordRepeate_input_layout_sign_up.setError("Các trường không được để trống");
            Email_input_layout_sign_up.setError("Các trường không được để trống");
            result = false;
        }
        if ( !passwordConfirm.equals(password)) {
            passwordRepeate_input_layout_sign_up.setError("Mật khẩu xác nhận chưa đúng");
            result = false;
        }
        return result;
    }

}