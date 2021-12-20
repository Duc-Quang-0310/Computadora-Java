package com.example.computadora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductsRecViewAdapter extends RecyclerView.Adapter<ProductsRecViewAdapter.ViewHolder> {

    ArrayList<displayProduct> products = new ArrayList<>();
    ArrayList<RequestReturn_Products> dps = new ArrayList<>();
    private Context context;

    public ProductsRecViewAdapter (Context context){
        this.context = context;
    }

    public void setProducts(ArrayList<displayProduct> products) {
        this.products = products;
        notifyDataSetChanged();
    }

    public void setDps(ArrayList<RequestReturn_Products> dps) {
        this.dps = dps;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_addWishList, img_addToCart, product_img;
        private TextView textPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_addWishList = itemView.findViewById(R.id.img_addWishList);
            img_addToCart = itemView.findViewById(R.id.img_addToCart);
            product_img = itemView.findViewById(R.id.product_img);
            textPrice = itemView.findViewById(R.id.textPrice);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textPrice.setText(dps.get(position).getPrice());
        Glide.with(context).asBitmap().load(dps.get(position).getImgs().get(0)).into(holder.product_img);

        holder.product_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductDetails.class );
                intent.putExtra("_id", dps.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dps.size();
    }


}

