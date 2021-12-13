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

public class BlogsRecViewAdapter extends RecyclerView.Adapter<BlogsRecViewAdapter.ViewHolder> {

    private ArrayList<displayBlog> blogs = new ArrayList<>();
    private  ArrayList<RequestReturn_Blog> datablogs = new ArrayList<>();
    private Context context;

    public BlogsRecViewAdapter( Context context ) {
        this.context = context;
    }

    public void setBlogs(ArrayList<displayBlog> blogs) {
        this.blogs = blogs;
        notifyDataSetChanged();
    }

    public void setDatablogs(ArrayList<RequestReturn_Blog> datablogs) {
        this.datablogs = datablogs;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(datablogs.get(position).getHeadline());
        Glide.with(context).asBitmap().load(datablogs.get(position).getImgHeadline().get(0)).into(holder.image);

//        holder.title.setText(blogs.get(position).getTitle());
//        Glide.with(context).asBitmap().load(blogs.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return datablogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_blog_rec);
            image = itemView.findViewById(R.id.image_blog_rec);
        }
    }
}
