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
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class BlogFragment extends Fragment {

    private CarouselView carouV;
    private RecyclerView blog_recyclerView;
    private RequestQueue requestQueue;
    private RelativeLayout search_rdr;

    private int[] images = new int[]{
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5
    };

    public BlogFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        initValue(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false);
    }

    private void initValue(FragmentActivity view) {
        carouV = view.findViewById(R.id.carousel_blog_frag);
        search_rdr = view.findViewById(R.id.search_rdr);
        blog_recyclerView = view.findViewById(R.id.blog_recyclerView);
        requestQueue = Volley.newRequestQueue(view);

        carouV.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(images[position]);
            }
        });
        carouV.setPageCount(images.length);

        search_rdr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, Search.class );
                startActivity(intent);
            }
        });

        ArrayList<displayBlog> blogs = new ArrayList<>();
        ArrayList<RequestReturn_Blog> dataBlogs = new ArrayList<>();
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));
        blogs.add(new displayBlog("This is a blog title", "https://simplepage.vn/blog/wp-content/uploads/2021/06/huong-dan-tao-blog-website.png"));

        //fetch
        fetchData(dataBlogs, view );
        //complete
    }

    private void fetchData (ArrayList<RequestReturn_Blog> dataBlogs, Context context) {
        String url = "https://backend-mobile-quang.herokuapp.com/blogs";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for( int i = 0 ; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        ArrayList<String> imgHeadline = new ArrayList<>();
                        ArrayList<String> blogs = new ArrayList<>();

                        //get all data from api
                        String _id = item.getString("_id");
                        String headline = item.getString("headline");
                        JSONArray imgHeadlineReturn = item.getJSONArray("imgHeadline");
                        JSONArray blogReturn = item.getJSONArray("blog");

                        //set with arr
                        imgHeadline.add(imgHeadlineReturn.get(0).toString());
                        for(int j = 0 ; j < blogReturn.length(); j++ ) {
                            blogs.add(blogReturn.get(j).toString());
                        }

                        //Bind to class
                        dataBlogs.add(new RequestReturn_Blog(imgHeadline, blogs, _id, headline ));

                        BlogsRecViewAdapter adapter = new BlogsRecViewAdapter( context);
                        adapter.setDatablogs(dataBlogs);
                        adapter.notifyDataSetChanged();
                        blog_recyclerView.setAdapter(adapter);
                        blog_recyclerView.setLayoutManager(new GridLayoutManager(context, 1));
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