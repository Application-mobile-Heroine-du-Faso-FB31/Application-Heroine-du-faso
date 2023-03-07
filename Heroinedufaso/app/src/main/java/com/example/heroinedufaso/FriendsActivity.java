package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FriendsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    UsersAdapter.OnUserClickListener onUserClickListener;

    private String myImageUrl;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);


        progressBar = findViewById(R.id.progressBar);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler);
        swipeRefreshLayout = findViewById(R.id.swiper_friends_activity);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getUsers();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        onUserClickListener = new UsersAdapter.OnUserClickListener() {
            @Override
            public void OnUserClicked(int position) {
                startActivity(new Intent(FriendsActivity.this, MessageActivity.class).
                        putExtra("username_of_roomate", users.get(position).getFullName())
                        .putExtra("id_receive", users.get(position).getUid())
                        .putExtra("img_of_roomate", users.get(position).getPhotoProfileURL())
                        .putExtra("my_img", myImageUrl)
                        .putExtra("user_id", users.get(position).getUid()));
//                Toast.makeText(FriendsActivity.this, "Tapped on user " + users.get(position).getFullName() , Toast.LENGTH_SHORT).show();
            }
        };

        getUsers();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu){
//        getMenuInflater().inflate(R.menu.profile_menu, menu);
//        return true;
//    }

    private void getUsers(){
        users.clear();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(!currentUser.getUid().equals(dataSnapshot.getValue(User.class).getUid())){
                        users.add(dataSnapshot.getValue(User.class));
                    }else{
                        myImageUrl = dataSnapshot.getValue(User.class).getPhotoProfileURL();
                    }
                }
                usersAdapter = new UsersAdapter(users, FriendsActivity.this, onUserClickListener);
                recyclerView.setLayoutManager(new LinearLayoutManager(FriendsActivity.this));
                recyclerView.setAdapter(usersAdapter);
                progressBar.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}