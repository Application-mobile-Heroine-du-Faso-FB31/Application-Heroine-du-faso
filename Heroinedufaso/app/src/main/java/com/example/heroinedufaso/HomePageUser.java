package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomePageUser extends AppCompatActivity {

    private Button profileBtn;
    private Button signOutBtn;

    private Button chatListBtn;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_user);

        profileBtn = (Button) findViewById(R.id.profile_usr_home_btn);
        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        chatListBtn = (Button) findViewById(R.id.chat);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePageUser.this, ProfileUserActivity.class);
                startActivity(i);
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomePageUser.this, MainActivity.class));
                }
            }
        });

        chatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageUser.this, FriendsActivity.class));
            }
        });
    }
}