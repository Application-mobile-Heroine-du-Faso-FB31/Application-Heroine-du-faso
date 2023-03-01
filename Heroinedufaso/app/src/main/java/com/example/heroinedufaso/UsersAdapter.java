package com.example.heroinedufaso;

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

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {

    private ArrayList<User> users;
    private Context context;

    private OnUserClickListener onUserClickListener;
    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }


    @NonNull
    @Override
    public UsersAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_holder,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersAdapter.UserHolder holder, int position) {
        holder.txtUsername.setText(users.get(position).getFullName());
        Glide.with(context).load(users.get(position).getPhotoProfileURL()).error(R.drawable.baseline_person_pink_24)
                .placeholder(R.drawable.baseline_person_pink_24).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView txtUsername;
        ImageView imageView;
        public UserHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            imageView = itemView.findViewById(R.id.img_pro);
        }
    }

    interface OnUserClickListener{
        void OnUserClicked(int position);
    }


}