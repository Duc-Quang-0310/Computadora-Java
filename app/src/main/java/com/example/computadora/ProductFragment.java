package com.example.computadora;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductFragment extends Fragment {

    private RecyclerView productView;
    private CarouselView carouselView;
    private int[] myImg = new int[]{
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5
    };
    private RelativeLayout search_rdr_p;
    private RequestQueue requestQueue;
    private static final String URL = "https://backend-mobile-quang.herokuapp.com/products";

    public ProductFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        initValue(getActivity());
    }

    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product, container, false);
    }

    private void initValue(FragmentActivity view){
        carouselView = view.findViewById(R.id.carousel_product_fragment);
        requestQueue = Volley.newRequestQueue(view);
        productView = view.findViewById(R.id.product_recyclerView);
        search_rdr_p = view.findViewById(R.id.search_rdr_p);

        search_rdr_p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, Search.class);
                startActivity(intent);
            }
        });

        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(myImg[position]);
            }
        });
        carouselView.setPageCount(myImg.length);


        ArrayList<RequestReturn_Products> dataProducts = new ArrayList<>();

        fetchData(dataProducts, view);
    }

    private void fetchData(ArrayList<RequestReturn_Products> dataProducts, Context context) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

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

                        ProductsRecViewAdapter adapter = new ProductsRecViewAdapter(context);
                        adapter.setDps(dataProducts);
                        adapter.notifyDataSetChanged();
                        productView.setAdapter(adapter);
                        productView.setLayoutManager(new GridLayoutManager(context, 2));

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