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

public class Account_ChangeInfo extends AppCompatActivity {

    private TextInputLayout inputCity_layout, inputDistrict_layout, inputSubDistrict_layout;
    private TextInputEditText inputCity, inputDistrict, inputSubDistrict;
    private Button submitChange_Info;
    private ImageView imageView;
    private RequestQueue requestQueue;
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String SHIP_CITY = "shipCity";
    private static final String SHIP_DISTRICT = "shipDistrict";
    private static final String SHIP_SUBDISTRICT = "shipSubDistrict";
    private static final String USERNAME = "username";
    private static final String UPDATE_URL = "https://backend-mobile-quang.herokuapp.com/api/updateUserInfo/updateShip";
    private String city, district, subDistrict, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_change_info);

        inputCity_layout = findViewById(R.id.inputCity_layout);
        inputDistrict_layout = findViewById(R.id.inputDistrict_layout);
        inputSubDistrict_layout = findViewById(R.id.inputSubDistrict_layout);

        inputCity = findViewById(R.id.inputCity);
        inputDistrict = findViewById(R.id.inputDistrict);
        inputSubDistrict = findViewById(R.id.inputSubDistrict);

        submitChange_Info = findViewById(R.id.submitChange_Info);

        imageView = findViewById(R.id.previousPage);

        requestQueue = Volley.newRequestQueue(this);

        checkValue();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        submitChange_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityIP = inputCity.getText().toString().trim();
                String districtIP = inputDistrict.getText().toString().trim();
                String subDistrictIP = inputSubDistrict.getText().toString().trim();

                Map<String, String> body = new HashMap<>();
                body.put("city", cityIP);
                body.put("district", districtIP);
                body.put("subDistrict", subDistrictIP);
                body.put("username", username);

                JSONObject parameters = new JSONObject(body);

                System.out.println(parameters);

                updateShipInfo(parameters, cityIP, districtIP, subDistrictIP);
            }
        });

    }

    private void checkValue() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        city = sharedPreferences.getString(SHIP_CITY, "");
        district = sharedPreferences.getString(SHIP_DISTRICT, "");
        subDistrict = sharedPreferences.getString(SHIP_SUBDISTRICT, "");
        username = sharedPreferences.getString(USERNAME, "");

        if (!city.equals("")) inputCity.setText(city);
        if (!district.equals("")) inputDistrict.setText(district);
        if (!subDistrict.equals("")) inputSubDistrict.setText(subDistrict);

    }

    private void updateShipInfo(JSONObject parameters, String cityIP,  String districtIP, String subDistrictIP ) {
        System.out.println("update ship info");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, UPDATE_URL, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    String message = response.getString("message");
                    if (success) {
                        Toast.makeText(Account_ChangeInfo.this, message, Toast.LENGTH_LONG).show();
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(SHIP_CITY, cityIP).apply();
                        editor.putString(SHIP_DISTRICT, districtIP).apply();
                        editor.putString(SHIP_SUBDISTRICT, subDistrictIP).apply();

                    } else {
                        Toast.makeText(Account_ChangeInfo.this, "Xin vui lòng thử lại sau", Toast.LENGTH_LONG).show();

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