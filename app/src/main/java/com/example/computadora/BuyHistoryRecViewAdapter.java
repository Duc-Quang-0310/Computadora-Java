package com.example.computadora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BuyHistoryRecViewAdapter extends RecyclerView.Adapter<BuyHistoryRecViewAdapter.ViewHolder> {

    private ArrayList<BuyHistoryItem> items = new ArrayList<>();
    private Context context;

    public void setItems(ArrayList<BuyHistoryItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public BuyHistoryRecViewAdapter(Context context ) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.buy_history_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.hour_history.setText(items.get(position).getHour());
        holder.date_history.setText(items.get(position).getDate());
        holder.button_toggle.setText("ID: " + items.get(position).get_id());
        holder.status_txt.setText(items.get(position).getStatus());
        Glide.with(context).asBitmap().load(items.get(position).getImgUrl()).into(holder.product_history_img);

        holder.button_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(items.get(position).getExpand());
                items.get(position).setExpand(!items.get(position).getExpand());
                System.out.println(items.get(position).getExpand());
                notifyDataSetChanged();
            }
        });

        if (items.get(position).getExpand()) {
            TransitionManager.beginDelayedTransition(holder.details_history);
            holder.details_history.setVisibility(View.VISIBLE);
            holder.toggle_img.setImageResource(R.drawable.ic_arr_up);
        }
        else {
            TransitionManager.beginDelayedTransition(holder.details_history);
            holder.details_history.setVisibility(View.GONE);
            holder.toggle_img.setImageResource(R.drawable.ic_arr_down);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private Button button_toggle;
        private ImageView product_history_img, toggle_img;
        private TextView status_txt, date_history, hour_history;
        private CardView details_history;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            button_toggle = itemView.findViewById(R.id.button_toggle);
            product_history_img = itemView.findViewById(R.id.product_history_img);
            toggle_img = itemView.findViewById(R.id.toggle_img);
            status_txt = itemView.findViewById(R.id.status_txt);
            date_history = itemView.findViewById(R.id.date_history);
            hour_history = itemView.findViewById(R.id.hour_history);
            details_history = itemView.findViewById(R.id.details_history);
        }
    }
}
