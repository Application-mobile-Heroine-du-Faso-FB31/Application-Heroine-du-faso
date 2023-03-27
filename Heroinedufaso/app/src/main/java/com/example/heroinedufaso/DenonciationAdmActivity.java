package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import static com.example.heroinedufaso.R.id.floatingActionButtonADM;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DenonciationAdmActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    public ArrayList<Post> posts;
    private FloatingActionButton floatingActionButton;
    private FirebaseUser user;

    private User currentUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denonciation_adm);

        progressBar = findViewById(R.id.progressBarPostAdm);
        recyclerView = findViewById(R.id.recyclerViewPostAdm);
        floatingActionButton = findViewById(floatingActionButtonADM);
        posts = new ArrayList<>();
        user = FirebaseAuth.getInstance().getCurrentUser();
        adapter = new PostAdapterAdm(posts, this);
        recyclerView.setAdapter(adapter);

        getCurrentUser();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DenonciationAdmActivity.this, CreatePostActivity.class));
            }
        });

        getPosts();
    }

    private void getPosts() {
        posts.clear();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("post");

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot s : snapshot.getChildren()){
                    Post post = s.getValue(Post.class);
                    Log.i(TAG, post.getOwner());
                    posts.add(post);
                }

                progressBar.setVisibility(View.GONE);
                floatingActionButton.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Log.i(TAG,"The read failed: " + error.getCode());
            }
        });




    }


    public void getCurrentUser(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").
                child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                currentUser = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });
    }
}