package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Auth_Login extends AppCompatActivity {

    private static final String LOGIN_API_URL = "https://backend-mobile-quang.herokuapp.com/api/user/login";
    private static final String TOKEN = "token";
    private static final String PHONE_NUMBER = "phonenumber";
    private static final String IMAGE_URL = "imageUrl";
    private static final String EMAIL = "email";
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String USERNAME = "username";
    private static final String SHIP_CITY = "shipCity";
    private static final String SHIP_DISTRICT = "shipDistrict";
    private static final String SHIP_SUBDISTRICT = "shipSubDistrict";
    private static final String USER_ID = "userID";

    private RequestQueue requestQueue;
    private TextView txt_Redirect_to_Sign_Up, txt_Redirect_to_PWRecover;
    private TextInputEditText login_username_input, login_password_input;
    private TextInputLayout username_input_layout, pasword_input_layout;
    private Button btn_Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_login);

        checkLogin();

        initValue();

        redirectText();

        handleSubmit();
    }

    private void checkLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN,"");
        if ( !token.equals("")) {
            SupportClass.setTimeout(() -> redirecter(), 0);
        }

        System.out.println("this is token" + token);
    }

    private void redirecter() {
        Intent intent = new Intent(Auth_Login.this, Home.class);
        startActivity(intent);
    }

    private void handleSubmit() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    private void login() {
        String username = login_username_input.getText().toString().trim();
        String password = login_password_input.getText().toString().trim();

        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", password);
        JSONObject parameters = new JSONObject(body);
        startLogin(parameters);
    }

    private void startLogin(JSONObject parameters) {
        System.out.println("logging in");
        String url = LOGIN_API_URL;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    if (success == true) {
                        JSONObject dataReturn = response.getJSONObject("data");
                        String token = dataReturn.getString("token");
                        JSONObject user = dataReturn.getJSONObject("user");

                        Date date = new Date();
                        String email = user.getString("email");
                        String imageUrl = user.getString("imageUrl");
                        Boolean isAdmin = user.getBoolean("isAdmin");
                        Boolean isConfirmed = user.getBoolean("isConfirmed");
                        String mobilePhone = user.getString("mobilePhone");
                        String name = user.getString("name");
                        String password = user.getString("password");
                        String shipCity = user.getString("shipCity");
                        String shipDistrict = user.getString("shipDistrict");
                        String shipSubDistrict = user.getString("shipSubDistrict");
                        String username = user.getString("username");
                        String _id = user.getString("_id");

                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        //store
                        editor.putString(TOKEN,token );
                        editor.putString(PHONE_NUMBER , mobilePhone);
                        editor.putString(IMAGE_URL,imageUrl );
                        editor.putString(PASSWORD , password);
                        editor.putString(NAME,name );
                        editor.putString(EMAIL,email );
                        editor.putString(SHIP_CITY , shipCity);
                        editor.putString(SHIP_DISTRICT,shipDistrict );
                        editor.putString(USERNAME,username);
                        editor.putString(SHIP_SUBDISTRICT , shipSubDistrict);
                        editor.putString(USER_ID,_id);
                        editor.commit();

                        Toast.makeText(Auth_Login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        SupportClass.setTimeout(() -> redirecter(), 500);
                    } else {
                        String message = response.getString("message").trim();
                        username_input_layout.setError(message);
                        pasword_input_layout.setError(message);
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
        Intent intent = new Intent(context, redirectTo);
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
        requestQueue = Volley.newRequestQueue(this);
    }
}