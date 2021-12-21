package com.example.computadora;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class Bought_History extends AppCompatActivity {

    private RecyclerView rv_bought_history;
    private ImageView back_rdr_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bought_history);

        rv_bought_history = findViewById(R.id.rv_bought_history);
        back_rdr_1 = findViewById(R.id.back_rdr_1);

        back_rdr_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ArrayList<BuyHistoryItem> items = new ArrayList<>();
        items.add(new BuyHistoryItem("123qunag123asdgqw", false, "https://res.cloudinary.com/dominhhai/image/upload/code/git.png","Đang xử lý","20/12/2021", "16:34"));
        items.add(new BuyHistoryItem("123qunag12356asdgqw", false, "https://res.cloudinary.com/dominhhai/image/upload/code/git.png","Đang xử lý","20/12/2021", "16:34"));

        BuyHistoryRecViewAdapter adapter = new BuyHistoryRecViewAdapter(this);
        adapter.setItems(items);

        rv_bought_history.setAdapter(adapter);
        rv_bought_history.setLayoutManager(new GridLayoutManager(this, 1));
    }
}