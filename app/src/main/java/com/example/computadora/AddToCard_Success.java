package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddToCard_Success extends AppCompatActivity {
    private static final int HOME_FRAGMENT = 1000523;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_card_success);

        Button rdrToHome = findViewById(R.id.button_RDRtoHome);

        rdrToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddToCard_Success.this, Home.class);
                intent.putExtra("view", HOME_FRAGMENT);
                startActivity(intent);
            }
        });
    }


}