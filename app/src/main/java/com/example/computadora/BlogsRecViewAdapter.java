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
import androidx.cardview.widget.CardView;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(datablogs.get(position).getHeadline());
        Glide.with(context).asBitmap().load(datablogs.get(position).getImgHeadline().get(0)).into(holder.image);

        holder.blog_list_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BlogDetails.class );
                intent.putExtra("_id", datablogs.get(position).get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datablogs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private ImageView image;
        private CardView blog_list_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text_blog_rec);
            image = itemView.findViewById(R.id.image_blog_rec);
            blog_list_item = itemView.findViewById(R.id.blog_list_item);
        }
    }
}
