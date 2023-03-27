package com.example.heroinedufaso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PostAdapterAdm extends RecyclerView.Adapter<PostAdapterAdm.ViewHolder> {
    private ArrayList<Post> posts;
    private Context context;

    public PostAdapterAdm(ArrayList<Post> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public PostAdapterAdm.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.post_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapterAdm.ViewHolder holder, int position) {
        Post post = posts.get(position);

        holder.username.setText(post.getUsername());
        holder.contains.setText(post.getContains());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username;
        TextView contains;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = (TextView) itemView.findViewById(R.id.usernameTxT);
            contains = itemView.findViewById(R.id.contains);
        }
    }
}

