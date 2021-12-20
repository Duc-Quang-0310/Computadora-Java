package com.example.computadora;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView cart_recyclerView;
    private Button submit_pay;

    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        initValue(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    private void initValue(FragmentActivity view) {
        cart_recyclerView = view.findViewById(R.id.cart_recyclerView);
        submit_pay = view.findViewById(R.id.submit_pay);

        ArrayList<CartItem> items = new ArrayList<>();

        items.add(new CartItem("a","b","2","https://image.freepik.com/free-photo/christmas-still-life-arrangement-with-copy-space_23-2149174206.jpg"));
        items.add(new CartItem("a","b","2","https://image.freepik.com/free-photo/christmas-still-life-arrangement-with-copy-space_23-2149174206.jpg"));


        CartRecViewAdapter adapter = new CartRecViewAdapter(view);
        adapter.setItems(items);

        cart_recyclerView.setAdapter(adapter);
        cart_recyclerView.setLayoutManager(new LinearLayoutManager(view));

        submit_pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view, Receipt.class );
                startActivity(intent); 
            }
        });


    }
}