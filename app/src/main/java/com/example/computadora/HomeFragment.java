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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView home_product_RecyclerView;
    private CarouselView carouselView;
    private int[] myImg = new int[]{
            R.drawable.logo_color, R.drawable.add_to_cart, R.drawable.welcome, R.drawable.ic_launcher_background, R.drawable.ic_launcher_foreground
    };
    private RequestQueue mQueue;
    private Button see_more_btn;
    private RelativeLayout search_zone_input;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        initValue(getActivity());
    }

    private void initValue(FragmentActivity view) {
        carouselView = view.findViewById(R.id.carousel_blog);

        search_zone_input = view.findViewById(R.id.search_zone_input);

        mQueue = Volley.newRequestQueue(view);
        home_product_RecyclerView = view.findViewById(R.id.home_product_RecyclerView);
        see_more_btn = view.findViewById(R.id.see_more_btn);

        //function
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(myImg[position]);
            }
        });
        carouselView.setPageCount(myImg.length);
        search_zone_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, Search.class);
                startActivity(intent);
            }
        });

        ArrayList<displayProduct> products = new ArrayList<>();
        ArrayList<RequestReturn_Products> dataProducts = new ArrayList<>();
        products.add(new displayProduct("29.990.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("28.590.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("27.590.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("26.990.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("25.590.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("24.990.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("23.590.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));
        products.add(new displayProduct("22.790.000 đ", "https://cdn.tgdd.vn/Products/Images/44/230240/hp-envy-13-ba1030tu-i7-2k0b6pa-101820-091857-600x600.jpg"));


        fetchData(dataProducts, view);

        see_more_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, ProductFragment.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    private void fetchData(ArrayList<RequestReturn_Products> dataProducts, Context context) {
        String url = "https://backend-mobile-quang.herokuapp.com/products";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    System.out.println(jsonArray.length());
                    for (int i = 0; i < 10; i++) {
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
                        home_product_RecyclerView.setAdapter(adapter);
                        home_product_RecyclerView.setLayoutManager(new GridLayoutManager(context, 2));

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
        mQueue.add(request);
    }


}