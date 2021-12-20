package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        SupportClass.setTimeout(() -> redirecter(), 1000);

    }

    private void redirecter() {
        Intent intent = new Intent(MainActivity.this,Auth_Login.class);
        startActivity(intent);
    }

}