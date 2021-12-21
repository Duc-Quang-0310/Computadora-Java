package com.example.computadora;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Locale;

public class ProductsRecViewAdapter extends RecyclerView.Adapter<ProductsRecViewAdapter.ViewHolder> {

    private ArrayList<displayProduct> products = new ArrayList<>();
    private ArrayList<RequestReturn_Products> dps = new ArrayList<>();
    private Context context;
    private static final String SHARED_PREF = "sharedPrefs";
    private static final String CART_ITEMS = "cart_items";

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

        holder.img_addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<CartItem>>(){}.getType();
                ArrayList<CartItem> items1 =  gson.fromJson(sharedPreferences.getString(CART_ITEMS,null),type );

                if (items1 == null) {
                    ArrayList<CartItem> tmp = new ArrayList();
                    tmp.add(new CartItem(dps.get(position).getName(), dps.get(position).getPrice(), 1, dps.get(position).getImgs().get(0)));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    setData(editor, tmp);
                }
                else {

                    System.out.println("Hello there");

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(CART_ITEMS).apply();
                    boolean exist = false;

                    for( int i = 0; i < items1.size();i++ ){
                        System.out.println(items1.size());
                        System.out.println(dps.size());
                        for( int j = 0; j <dps.size(); j++) {
                            if ( dps.get(j).getName().toLowerCase(Locale.ROOT).equals(items1.get(i).getName().toLowerCase(Locale.ROOT)) ){
                                exist = true;
                                items1.get(i).setQuantity( items1.get(i).getQuantity() + 1 );
                            }
                        }

                    }
                    if ( !exist ) items1.add(new CartItem(dps.get(position).getName(), dps.get(position).getPrice(), 1, dps.get(position).getImgs().get(0)));

                    setData(editor, items1);
                }

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dps.size();
    }

    private void setData (SharedPreferences.Editor editor, ArrayList<CartItem> array ) {
        Gson gson1 = new Gson();
        editor.putString(CART_ITEMS, gson1.toJson(array));
        editor.commit();
        Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
        notifyDataSetChanged();
    }
}

