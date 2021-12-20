package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Home extends AppCompatActivity {

    private BottomNavigationView bottomNav;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private static final int HOME_FRAGMENT = 1000523;
    private static final int PRODUCT_FRAGMENT = 1000367;
    private static final int CART_FRAGMENT = 1000013;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNav = findViewById(R.id.bottomNavigationView);
        navController = Navigation.findNavController(this, R.id.fragment);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.homeFragment, R.id.productFragment, R.id.accountFragment, R.id.blogFragment, R.id.cartFragment).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);

        //handle navigate with nav
        int viewId = getIntent().getIntExtra("view", HOME_FRAGMENT);
        if (viewId == PRODUCT_FRAGMENT) bottomNav.setSelectedItemId(R.id.productFragment);
        if (viewId == HOME_FRAGMENT) bottomNav.setSelectedItemId(R.id.homeFragment);
        if (viewId == CART_FRAGMENT) bottomNav.setSelectedItemId(R.id.cartFragment);
    }

}