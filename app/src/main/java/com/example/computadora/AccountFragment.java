package com.example.computadora;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AccountFragment extends Fragment {

    private static final String TOKEN = "token";
    private static final String PHONE_NUMBER = "phonenumber";
    private static final String IMAGE_URL = "imageUrl";
    private static final String EMAIL = "email";
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SHIP_CITY = "shipCity";
    private static final String SHIP_DISTRICT = "shipDistrict";
    private static final String SHIP_SUBDISTRICT = "shipSubDistrict";

    private ConstraintLayout changeInfo_redirect, boughHistory_redirect, changePW_redirect, logout_redirect;


    public AccountFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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

        onClickRedirectHandler(changeInfo_redirect, Account_ChangeInfo.class);
        onClickRedirectHandler(changePW_redirect, Account_ChangePW.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private void onClickRedirectHandler(ConstraintLayout layout, Class finalDestination) {
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecter(finalDestination);
            }
        });
    }

    private void redirecter(Class classDirector) {
        Intent intent = new Intent(getContext(), classDirector);
        startActivity(intent);
    }

    private void initValue(FragmentActivity view) {
        changeInfo_redirect = view.findViewById(R.id.changeInfo_redirect);
        boughHistory_redirect = view.findViewById(R.id.boughHistory_redirect);
        changePW_redirect = view.findViewById(R.id.changePW_redirect);
        logout_redirect = view.findViewById(R.id.logout_redirect);
        SharedPreferences sharedPreferences = view.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        changeInfo_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecter(Account_ChangeInfo.class);
            }
        });

        logout_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear().commit();
                redirecter(Auth_Login.class);
            }
        });
    }


}