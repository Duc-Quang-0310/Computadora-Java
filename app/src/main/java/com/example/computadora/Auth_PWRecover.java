package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Auth_PWRecover extends AppCompatActivity {

    private final static String RECOVER_URL = "https://backend-mobile-quang.herokuapp.com/api/user/pwRecoverSendRequestToBackEnd";
    private RequestQueue requestQueue;
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
//      Volley
        requestQueue = Volley.newRequestQueue(this);

        //handle onClick

        btn_submit_PWR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = login_username_input_PWR.getText().toString().trim();
                String password = login_password_input_PWR.getText().toString().trim();
                String pwConfirm = login_passwordRepeat_input_PWR.getText().toString().trim();
                String email = login_Email_input_PWR.getText().toString().trim();
                boolean ok = verify(username , password , pwConfirm,email );
                if( ok ) {
                    Map<String, String> body = new HashMap<>();
                    body.put("username",username);
                    body.put("password",password);
                    body.put("email",email);
                    JSONObject parameters = new JSONObject(body);
                    recover(parameters);
                }
            }
        });
    }

    private void recover (JSONObject parameters) {
        System.out.println("recover password");
        String url = RECOVER_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    if (success == true) {
                        String message = response.getString("message");
                        Toast.makeText(Auth_PWRecover.this, message, Toast.LENGTH_LONG).show();
                        redirectListener(Auth_PWRecover.this, Auth_Login.class);
                    } else {
                        String message = response.getString("message");
                        username_input_layout_PWR.setError(message);
                        password_input_layout_PWR.setError(message);
                        passwordRepeate_input_layout_PWR.setError(message);
                        Email_input_layout_PWR.setError(message);
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
            username_input_layout_PWR.setError("Các trường không được để trống");
            password_input_layout_PWR.setError("Các trường không được để trống");
            passwordRepeate_input_layout_PWR.setError("Các trường không được để trống");
            Email_input_layout_PWR.setError("Các trường không được để trống");
            result = false;
        }
        if ( !passwordConfirm.equals(password)) {
            passwordRepeate_input_layout_PWR.setError("Mật khẩu xác nhận chưa đúng");
            result = false;
        }
        return result;
    }
}