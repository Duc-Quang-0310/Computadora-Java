package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class Account_ChangePW extends AppCompatActivity {

    private TextInputLayout inputPW_layout, inputNewPW_layout, inputNewPWRepeat_layout;
    private TextInputEditText inputPW, inputNewPW, inputNewPWRepeat;
    private Button submitChange_PW;
    private ImageView imageView;
    private String username;
    private RequestQueue requestQueue;
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String USERNAME = "username";
    private static final String UPDATE_URL = "https://backend-mobile-quang.herokuapp.com/api/updateUserInfo/updatePassWord";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_pw);

        initValue();

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

    private void initValue() {
        inputPW_layout = findViewById(R.id.inputPW_layout);
        inputNewPW_layout = findViewById(R.id.inputNewPW_layout);
        inputNewPWRepeat_layout = findViewById(R.id.inputNewPWRepeat_layout);

        inputPW = findViewById(R.id.inputPW);
        inputNewPW = findViewById(R.id.inputNewPW);
        inputNewPWRepeat = findViewById(R.id.inputNewPWRepeat);

        submitChange_PW = findViewById(R.id.submitChange_PW);

        imageView = findViewById(R.id.previousPage_changePW);

        requestQueue = Volley.newRequestQueue(this);

    }

    private void submit() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME, "");

        String pwInput = inputPW.getText().toString().trim();
        String npwInput = inputNewPW.getText().toString().trim();
        String npwRpInput = inputNewPWRepeat.getText().toString().trim();

        if( !validate(pwInput, npwInput, npwRpInput) ){
            Map<String, String> body = new HashMap<>();
            body.put("password", pwInput);
            body.put("username", username);
            body.put("newPassword", npwInput);
            JSONObject parameters = new JSONObject(body);
            changePw(parameters);
        }

    }

    private boolean validate(String password, String npw, String npwRp ) {
        boolean error = false;
        if( password.equals("") || npw.equals("") || npwRp.equals("")){
            inputPW_layout.setError("Các trường không được để trống");
            inputNewPW_layout.setError("Các trường không được để trống");
            inputNewPWRepeat_layout.setError("Các trường không được để trống");
            error = true;
        }
        if( !npwRp.equals(npw) ){
            inputNewPWRepeat_layout.setError("Mật khẩu xác nhận không đúng");
            error = true;
        }
        return error;
    }

    private void changePw (JSONObject parameters) {
        System.out.println("Change password");
        String url = UPDATE_URL;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    String message = response.getString("message");
                    if (success) {
                        Toast.makeText(Account_ChangePW.this, message, Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(Account_ChangePW.this, message, Toast.LENGTH_SHORT).show();
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
        requestQueue.add(jsonObjectRequest);
    }
}