package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Search extends AppCompatActivity {
    private EditText input_search;
    private Button submit_search;
    private RecyclerView result_recyclerView;
    private RequestQueue requestQueue;
    private TextView back_txt;
    private ImageView back_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initValue();
    }

    private void initValue() {
        input_search = findViewById(R.id.input_search);
        submit_search = findViewById(R.id.submit_search);
        result_recyclerView = findViewById(R.id.result_recyclerView);
        requestQueue = Volley.newRequestQueue(this);
        back_txt = findViewById(R.id.back_txt);
        back_image = findViewById(R.id.back_image);

        submit_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = input_search.getText().toString();
                System.out.println(name);

                Map<String, String> body = new HashMap<>();
                body.put("name", name);

                JSONObject parameters = new JSONObject(body);
                fetchSearchResult(parameters);
            }
        });

        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        back_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchSearchResult (JSONObject parameters) {
        System.out.println("fetching search result");
        String url = "https://backend-mobile-quang.herokuapp.com/products/search";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("data");
                    ArrayList<RequestReturn_Products> dataProducts = new ArrayList<>();

                    System.out.println(jsonArray.length());
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        ArrayList<String> reviews = new ArrayList<>();
                        ArrayList<String> imgs = new ArrayList<>();

                        //get all data from api
                        String _id = item.getString("_id");
                        String name = item.getString("name");
                        String chip = item.getString("chip");
                        String screen = item.getString("screen");
                        String color = item.getString("color");
                        String ram = item.getString("ram");
                        String card = item.getString("card");
                        String storage = item.getString("storage");
                        String pin = item.getString("pin");
                        String connection = item.getString("connection");
                        String weight = item.getString("weight");
                        String window = item.getString("window");
                        String price = item.getString("price");

                        JSONArray reviewReturn = item.getJSONArray("review");
                        JSONArray imgsReturn = item.getJSONArray("imgs");

                        //set with arr
                        if (reviewReturn.length() <= 1) {
                            reviews.add("Đang cập nhật review mong các bạn thông cảm");
                        } else {
                            for (int j = 0; j < reviewReturn.length(); j++) {
                                reviews.add(reviewReturn.get(j).toString());
                            }
                        }

                        for (int k = 0; k < imgsReturn.length(); k++) {
                            imgs.add(imgsReturn.get(k).toString());
                        }
                        dataProducts.add(new RequestReturn_Products(reviews, imgs, _id, name, chip, screen, color, ram, card, storage, pin, connection, weight, window, price));

                        //Bind to class

                        ProductsRecViewAdapter adapter = new ProductsRecViewAdapter(Search.this);
                        adapter.setDps(dataProducts);
                        adapter.notifyDataSetChanged();
                        result_recyclerView.setAdapter(adapter);
                        result_recyclerView.setLayoutManager(new GridLayoutManager(Search.this, 2));

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