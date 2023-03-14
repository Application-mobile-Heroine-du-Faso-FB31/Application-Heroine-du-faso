package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<User> users;
    private ProgressBar progressBar;
    private UsersAdapter usersAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    UsersAdapter.OnUserClickListener onUserClickListener;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        progressBar = findViewById(R.id.progressBarAdmUserListActivity);
        users = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerAdmUserListActivity);
        swipeRefreshLayout = findViewById(R.id.swiper_userList_activity);

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
                startActivity(new Intent(UserListActivity.this, UserManagementActivity.class).
                        putExtra("full_name_of_the_user", users.get(position).getFullName())
                        .putExtra("id_of_the_user", users.get(position).getUid())
                        .putExtra("img_of_the_user", users.get(position).getPhotoProfileURL())
                        .putExtra("role", users.get(position).getRole())
                        .putExtra("city", users.get(position).getCity())
                        .putExtra("phone_number", users.get(position).getPhoneNumber()));
            }
        };


        getUsers();
    }

    private void getHealthSpecialist(){

        FirebaseDatabase.getInstance().getReference("healthSpecialist").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    Log.i(TAG, "data snapshot " + dataSnapshot.getValue().toString());
                    User user = (User) dataSnapshot.getValue(HealthSpecialist.class);
//                    Log.i(TAG, "health specialist : " + user.getFullName());
                    users.add(user);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


    private void getUsers(){
        getHealthSpecialist();
        users.clear();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    if(!currentUser.getUid().equals(dataSnapshot.getValue(User.class).getUid())){
                        users.add(dataSnapshot.getValue(User.class));
                    }
                }

                createAdapter();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }

    private void createAdapter(){
        usersAdapter = new UsersAdapter(users, UserListActivity.this, onUserClickListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserListActivity.this));
        recyclerView.setAdapter(usersAdapter);
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}