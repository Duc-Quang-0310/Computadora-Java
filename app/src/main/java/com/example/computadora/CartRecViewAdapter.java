package com.example.computadora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class CartRecViewAdapter extends RecyclerView.Adapter<CartRecViewAdapter.ViewHolder> {

    private static final String SHARED_PREF = "sharedPrefs";
    private static final String CART_ITEMS = "cart_items";
    private ArrayList<CartItem> items = new ArrayList<>();
    private Context context;

    public CartRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.name_txt.setText(items.get(position).getName());
        holder.price_txt.setText(items.get(position).getPrice());
        holder.quantity_txt.setText(items.get(position).getQuantity().toString());
        Glide.with(context).asBitmap().load(items.get(position).getImageUrl()).into(holder.cart_item_img);

        holder.minus_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                if(items.get(position).getQuantity() == 1) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    items.remove(position);
                    Gson gson = new Gson();
                    editor.putString(CART_ITEMS, gson.toJson(items));
                    editor.apply();
                    notifyDataSetChanged();
                }
                else{
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    items.get(position).setQuantity(items.get(position).getQuantity() - 1);

                    Gson gson = new Gson();
                    editor.putString(CART_ITEMS, gson.toJson(items));
                    editor.apply();
                    notifyDataSetChanged();
                }
            }
        });

        holder.add_quantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                items.get(position).setQuantity(items.get(position).getQuantity() + 1);
                ArrayList<CartItem> array = items;
                Gson gson = new Gson();
                editor.putString(CART_ITEMS, gson.toJson(array));
                editor.apply();
                notifyDataSetChanged();
            }
        });
//
        holder.delete_cart_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                items.remove(position);
                Gson gson = new Gson();
                editor.putString(CART_ITEMS, gson.toJson(items));
                editor.apply();
                notifyDataSetChanged();
            }
        });
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
        private ImageView cart_item_img, minus_quantity, add_quantity, delete_cart_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name_txt= itemView.findViewById(R.id.name_txt);
            price_txt= itemView.findViewById(R.id.price_txt);
            quantity_txt= itemView.findViewById(R.id.quantity_txt);
            cart_item_img = itemView.findViewById(R.id.cart_item_img);
            minus_quantity= itemView.findViewById(R.id.minus_quantity);
            add_quantity= itemView .findViewById(R.id.add_quantity);
            delete_cart_item = itemView.findViewById(R.id.delete_cart_item);
        }
    }
}
