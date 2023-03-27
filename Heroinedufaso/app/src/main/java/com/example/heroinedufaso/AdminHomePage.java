package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AdminHomePage extends AppCompatActivity {

    private Button profile, chat, forumManagement, consultation, news, periodCalculator, userList, quizBtn, notification, signOut;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        profile = findViewById(R.id.profile_adm_home_btn);
        chat = findViewById(R.id.chat_adm);
        forumManagement = findViewById(R.id.forum_adm);
        consultation = findViewById(R.id.consultation_adm);
        news = findViewById(R.id.publication_adm);
        periodCalculator = findViewById(R.id.menstruel_adm);
        userList = findViewById(R.id.user_list_adm);
        quizBtn = findViewById(R.id.quiz_home_adm_activity);
        signOut = findViewById(R.id.sign_out_btn_adm);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();



        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(AdminHomePage.this, MainActivity.class));
                    finish();
                }
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this, FriendsActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminHomePage.this, ProfileUserActivity.class);
                i.putExtra("role", "manager");
                startActivity(i);
            }
        });

        periodCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this, CyclesEngineActivity.class));
            }
        });

        userList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this, UserListActivity.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminHomePage.this, DenonciationAdmActivity.class));
            }
        });

    }
}