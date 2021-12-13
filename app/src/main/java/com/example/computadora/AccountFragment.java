package com.example.computadora;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    private ConstraintLayout changeInfo_redirect, boughHistory_redirect, changePW_redirect, logout_redirect;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        initValue(getActivity());

        onClickRedirectHandler(changeInfo_redirect,Account_ChangeInfo.class);
        onClickRedirectHandler(changePW_redirect,Account_ChangePW.class);
        onClickRedirectHandler(logout_redirect,Auth_Login.class);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private void onClickRedirectHandler(ConstraintLayout layout, Class finalDestination){
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecter(finalDestination);
            }
        });
    }


    private void redirecter(Class classDirector) {
        Intent intent = new Intent(getContext(),classDirector);

        startActivity(intent);
    }

    private void initValue(FragmentActivity view) {
        changeInfo_redirect = view.findViewById(R.id.changeInfo_redirect);
        boughHistory_redirect = view.findViewById(R.id.boughHistory_redirect);
        changePW_redirect = view.findViewById(R.id.changePW_redirect);
        logout_redirect = view.findViewById(R.id.logout_redirect);

        changeInfo_redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirecter(Account_ChangeInfo.class);
            }
        });
    }


}