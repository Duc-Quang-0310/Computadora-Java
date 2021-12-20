package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlogDetails extends AppCompatActivity {

    private String id;
    private RequestQueue requestQueue;
    private TextView my_txt_details;
    private ImageView img_finish, main_img;
    private RecyclerView Rv_blog_details;


    private String combineUrl(String id) {
        return "https://backend-mobile-quang.herokuapp.com/blogs/" + id;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_details);
        requestQueue = Volley.newRequestQueue(this);
        my_txt_details = findViewById(R.id.headLine_txt);
        img_finish = findViewById(R.id.img_finish);
        main_img = findViewById(R.id.main_img);
        Rv_blog_details = findViewById(R.id.Rv_blog_details);
        id = getIntent().getStringExtra("_id");

        fetchBlogDetails(id);

        img_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchBlogDetails(String finalID) {
        System.out.println("Fetching Details");
        String url = combineUrl(finalID);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray imgHeadLines = jsonObject.getJSONArray("imgHeadline");
                    JSONArray blogs = jsonObject.getJSONArray("blog");
                    String headline = jsonObject.getString("headline");
                    List<String> blogDetails = new ArrayList<>();

                    for (int i = 0; i < blogs.length(); i++) {
                        blogDetails.add(blogs.get(i).toString());
                    }

                    System.out.println(blogDetails);
                    my_txt_details.setText(headline);

                    Glide.with(BlogDetails.this).asBitmap().load(imgHeadLines.get(0).toString()).into(main_img);

                    BlogContentAdapter adapter = new BlogContentAdapter(BlogDetails.this, R.layout.item_image_view, R.layout.item_text_view, blogDetails);
                    Rv_blog_details.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    GridLayoutManager layoutManager = new GridLayoutManager(BlogDetails.this, 1);

                    Rv_blog_details.setLayoutManager(layoutManager);

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