package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {
    private TextView product_Name, displayName, displayChip, displayScreen, displayColor, displayRam, displayCard, displayMemory, displayPin, displayConnection, displayWeight, displayWindow, displayPrice;
    private ImageView rdr_back, rdr_cart, prev_product_img, next_product_img, displayProductImg;
    private RecyclerView displayDescription;
    private Button cart_add_btn, buy_btn;
    private String id;
    private RequestQueue requestQueue;
    private int index = 0;
    private static final int CART_FRAGMENT = 1000013;

    private String combineUrl(String id) {
        return "https://backend-mobile-quang.herokuapp.com/products/" + id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        id = getIntent().getStringExtra("_id");
        requestQueue = Volley.newRequestQueue(this);

        //bind value
        product_Name = findViewById(R.id.product_Name);
        displayName = findViewById(R.id.displayName);
        displayChip = findViewById(R.id.displayChip);
        displayScreen = findViewById(R.id.displayScreen);
        displayColor = findViewById(R.id.displayColor);
        displayRam = findViewById(R.id.displayRam);
        displayCard = findViewById(R.id.displayCard);
        displayMemory = findViewById(R.id.displayMemory);
        displayPin = findViewById(R.id.displayPin);
        displayConnection = findViewById(R.id.displayConnection);
        displayWeight = findViewById(R.id.displayWeight);
        displayWindow = findViewById(R.id.displayWindow);
        displayPrice = findViewById(R.id.displayPrice);
        rdr_back = findViewById(R.id.rdr_back);
        rdr_cart = findViewById(R.id.rdr_cart);
        prev_product_img = findViewById(R.id.prev_product_img);
        next_product_img = findViewById(R.id.next_product_img);
        displayDescription = findViewById(R.id.displayDescription);
        cart_add_btn = findViewById(R.id.cart_add_btn);
        buy_btn = findViewById(R.id.buy_btn);
        displayProductImg = findViewById(R.id.displayProductImg);

        rdr_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rdr_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, Home.class);
                intent.putExtra("view", CART_FRAGMENT);
                startActivity(intent);
            }
        });

        fetchProductDetails(id);
    }

    private String howItRender(String tag) {
        String result = tag != null ? tag : "Đang cập nhật";
        return result;
    }

    private void fetchProductDetails(String finalID) {
        System.out.println("Fetching Details");
        String url = combineUrl(finalID);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    ArrayList<String> reviews = new ArrayList<>();
                    ArrayList<String> images = new ArrayList<>();

                    String name = howItRender(jsonObject.getString("name"));
                    String chip = howItRender(jsonObject.getString("chip"));
                    String screen = howItRender(jsonObject.getString("screen"));
                    String color = howItRender(jsonObject.getString("color"));
                    String ram = howItRender(jsonObject.getString("ram"));
                    String card = howItRender(jsonObject.getString("card"));
                    String memory = howItRender(jsonObject.getString("storage"));
                    String pin = howItRender(jsonObject.getString("pin"));
                    String connection = howItRender(jsonObject.getString("connection"));
                    String weight = howItRender(jsonObject.getString("weight"));
                    String window = howItRender(jsonObject.getString("window"));
                    String price = howItRender(jsonObject.getString("price"));

                    JSONArray jsonArray = jsonObject.getJSONArray("review");
                    JSONArray jsonArray1 = jsonObject.getJSONArray("imgs");

                    if (jsonArray.length() == 0) {
                        reviews.add("Đang cập nhật vui lòng quay lại sau");
                    } else {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            reviews.add(jsonArray.get(i).toString());
                        }
                    }

                    for (int j = 0; j < jsonArray1.length(); j++) {
                        images.add(jsonArray1.get(j).toString());
                    }

                    setValue(name, chip, screen, color, ram, card, pin, memory, connection, weight, window, price, reviews, images);

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

    private void setValue(String name, String chip, String screen, String color, String ram, String card, String pin, String memory, String connection, String weight, String window, String price, List<String> reviews, ArrayList<String> images) {
        product_Name.setText(name);
        displayName.setText(name);
        displayChip.setText(chip);
        displayScreen.setText(screen);
        displayColor.setText(color);
        displayRam.setText(ram);
        displayCard.setText(card);
        displayMemory.setText(memory);
        displayPin.setText(pin);
        displayConnection.setText(connection);
        displayWeight.setText(weight);
        displayWindow.setText(window);
        displayPrice.setText(price);
        Glide.with(ProductDetails.this).asBitmap().load(images.get(index)).into(displayProductImg);

        BlogContentAdapter adapter = new BlogContentAdapter(ProductDetails.this, R.layout.item_image_view, R.layout.item_text_view, reviews);
        displayDescription.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        GridLayoutManager layoutManager = new GridLayoutManager(ProductDetails.this, 1);
        displayDescription.setLayoutManager(layoutManager);

        prev_product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == 0) {
                    index = images.size();
                }
                index--;
                Glide.with(ProductDetails.this).asBitmap().load(images.get(index)).into(displayProductImg);
            }
        });

        next_product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index == images.size()) {
                    index = 0;
                }
                index++;
                Glide.with(ProductDetails.this).asBitmap().load(images.get(index)).into(displayProductImg);
            }
        });

        cart_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProductDetails.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
            }
        });

        buy_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetails.this, Receipt.class);
                intent.putExtra("_id", id);
                startActivity(intent);
            }
        });


    }
}