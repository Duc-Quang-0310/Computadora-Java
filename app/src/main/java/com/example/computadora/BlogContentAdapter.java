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

import java.util.List;

public class BlogContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int imageView, textView;
    private List<String> contents;

    private final int IMAGE_VIEW = 1;
    private final int TEXT_VIEW = 2;

    public BlogContentAdapter(Context context, int imageView, int textView, List<String> contents) {
        this.context = context;
        this.imageView = imageView;
        this.textView = textView;
        this.contents = contents;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if(viewType == IMAGE_VIEW){
            v = layoutInflater.inflate(imageView, null);
            return new ImageViewHolder(v);
        }else{
            v = layoutInflater.inflate(textView, null);
            return new TextViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageViewHolder){
            Glide.with(context).asBitmap().load(contents.get(position)).into( ((ImageViewHolder) holder).iv);
        }else{
            ((TextViewHolder) holder).tv.setText(contents.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        if(contents.get(position).contains("https")){
            return IMAGE_VIEW;
        }else{
            return TEXT_VIEW;
        }
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }

    public  class ImageViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image_view);
        }
    }
    public  class TextViewHolder extends RecyclerView.ViewHolder{
        TextView tv;
        public TextViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.txt_view);
        }
    }
}
