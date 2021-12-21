package com.example.computadora;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartFragment extends Fragment {
    private RecyclerView cart_recyclerView;
    private Button submit_pay;
    private SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String CART_ITEMS = "cart_items";

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
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
        ArrayList<CartItem> items1 =  gson.fromJson(sharedPreferences.getString(CART_ITEMS,null),type );

        if ( items1 ==  null ) submit_pay.setVisibility(View.GONE);
        else {
            CartRecViewAdapter adapter = new CartRecViewAdapter(view);
            adapter.setItems(items1);

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
}