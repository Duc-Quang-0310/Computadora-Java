package com.example.computadora;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CartRecViewAdapter extends RecyclerView.Adapter<CartRecViewAdapter.ViewHolder> {

    private ArrayList<CartItem> items = new ArrayList<>();
    private Context context;

    public CartRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // thằng này của t là nó gắn cái cart_list-item xml file vào recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_txt.setText(items.get(position).getName());
        holder.price_txt.setText(items.get(position).getPrice());
        holder.quantity_txt.setText(items.get(position).getQuantity());
        Glide.with(context).asBitmap().load(items.get(position).getImageUrl()).into(holder.cart_item_img);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        private TextView name_txt, price_txt, quantity_txt;
        private ImageView cart_item_img;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt= itemView.findViewById(R.id.name_txt);
            price_txt= itemView.findViewById(R.id.price_txt);
            quantity_txt= itemView.findViewById(R.id.quantity_txt);
            cart_item_img = itemView.findViewById(R.id.cart_item_img);
        }
    }
}
