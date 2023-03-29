package com.example.heroinedufaso;



import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomePageUser extends AppCompatActivity {

    private Button profileBtn;
    private Button signOutBtn;

    private Button chatListBtn;

    private Button periodEngineBtn;
    private Button consultationBtn;

    private Button startQuizz;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private Button postBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_user);

        profileBtn = (Button) findViewById(R.id.profile_usr_home_btn);
        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        chatListBtn = (Button) findViewById(R.id.chat);
        periodEngineBtn = (Button) findViewById(R.id.menstruel);
        startQuizz=  (Button) findViewById(R.id.quiz_home_user_activity);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        postBtn = findViewById(R.id.denoncer);
        consultationBtn = findViewById(R.id.consultation);

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePageUser.this, ProfileUserActivity.class);
                startActivity(i);
            }
        });

        consultationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageUser.this, UserConsultation.class));
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomePageUser.this, MainActivity.class));
                    finish();
                }
            }
        });

        chatListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageUser.this, FriendsActivity.class));
            }
        });

        periodEngineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageUser.this, CyclesEngineActivity.class));
            }
        });

        startQuizz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePageUser.this, QuizzActivity.class);
                startActivity(i);
            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageUser.this, DenociationActivity.class));
            }
        });

    }
}