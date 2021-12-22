package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Receipt extends AppCompatActivity {

    private TextView txtName, txtAddress, txtPhone, txtMoney, txtChangePaymentInfo  ;
    private Button submit_payment;
    private ImageView back_payment_image;
    private RequestQueue requestQueue;
    private static final String PHONE_NUMBER = "phonenumber";
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String NAME = "name";
    private static final String SHIP_CITY = "shipCity";
    private static final String SHIP_DISTRICT = "shipDistrict";
    private static final String SHIP_SUBDISTRICT = "shipSubDistrict";
    private static final String CART_ITEMS = "cart_items";
    private static final String USER_ID = "userID";
    private static final String URL = "https://backend-mobile-quang.herokuapp.com/receipts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        initValue();

    }

    private void initValue () {
        txtName = findViewById(R.id.txtName);
        txtAddress = findViewById(R.id.txtAddress);
        txtPhone = findViewById(R.id.txtPhone);
        txtMoney = findViewById(R.id.txtMoney);
        txtChangePaymentInfo = findViewById(R.id.txtChangePaymentInfo);
        submit_payment = findViewById(R.id.submit_payment);
        back_payment_image = findViewById(R.id.back_payment_image);
        requestQueue = Volley.newRequestQueue(this);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        ArrayList<CartItem> items =  gson.fromJson(sharedPreferences.getString(CART_ITEMS,null),type );
        String name = sharedPreferences.getString(NAME, "Tài khoản");
        String city = sharedPreferences.getString(SHIP_CITY, "Hà Nội");
        String district = sharedPreferences.getString(SHIP_DISTRICT, "Từ Liêm");
        String subDistrict = sharedPreferences.getString(SHIP_SUBDISTRICT,"Bưu cục" );
        String phoneNumber = sharedPreferences.getString(PHONE_NUMBER, "Đang cập nhật");
        String userID = sharedPreferences.getString(USER_ID, null);;


        setValue(name, city, district, subDistrict, phoneNumber, items);

        back_payment_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtChangePaymentInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Receipt.this, Account_ChangeInfo.class);
                startActivity(intent);
            }
        });

        submit_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String  > body = new HashMap<>();
                body.put("userID", userID != null ? userID : "60cd5542e81ee11e5c1790ea");

                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove(CART_ITEMS).apply();

                Intent intent = new Intent(Receipt.this, Payment_Success.class);
                startActivity(intent);
            }
        });

    }

    private void setValue ( String name, String city, String district, String subDistrict, String phoneNumber, ArrayList<CartItem> items ) {
        //TODO: set value for txtName, txtAddress, txtPhone, txtMoney, txtChangePaymentInfo;
        txtName.setText(name);
        txtAddress.setText(subDistrict + ", " + district + ", " + city);
        txtPhone.setText(phoneNumber);
        txtMoney.setText(convertToNumber(items).toString());

    }

    private Integer convertToNumber (ArrayList<CartItem> items) {
        ArrayList<String> prices = new ArrayList<>();
        for( int i = 0; i < items.size(); i ++) {
            String[] parts = items.get(i).getPrice().split("\\s");
            for( String p: parts  ){
                if (!p.equals("₫")){
                    prices.add(p);
                }
            }
        }

        ArrayList<String> finalPrices = new ArrayList<>();
        for( int j = 0; j <prices.size(); j++ ){
            String[] parts = prices.get(j).split(Pattern.quote("."));
            String tmp = "";
            for( String p: parts  ){
                tmp += p;
            }
            finalPrices.add(tmp);
        }

        Integer price = 0;
        for ( int k = 0; k < finalPrices.size(); k++){
            int number = Integer.parseInt(finalPrices.get(k));
            price += number;
        }
        return price;
    }

    private void sendReceipt (JSONObject parameters){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Boolean success = response.getBoolean("success");
                    if (success == true) {
                        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove(CART_ITEMS).apply();

                        Intent intent = new Intent(Receipt.this, Payment_Success.class);
                        startActivity(intent);

                        Toast.makeText(Receipt.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Receipt.this, "Có lỗi xảy ra xin vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
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
}