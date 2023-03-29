package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePageHealthSpecialist extends AppCompatActivity {

    private Button profileBtn;
    private Button signOutBtn;

    private Button chatConsultationBtn;

    private Button denonciationButton;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private Button quizBtn, periodCalculatorBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_health_specialist);

        profileBtn = (Button) findViewById(R.id.profile_health_specialist_home_btn);
        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        chatConsultationBtn = (Button) findViewById(R.id.chat_specialist_consultation);
        denonciationButton = (Button) findViewById(R.id.denoncer_specialist);
        periodCalculatorBtn = (Button) findViewById(R.id.menstruel_specialist);
        quizBtn = (Button) findViewById(R.id.quiz_home_specialist_activity);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        chatConsultationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, ConsultationSpecialist.class));
            }
        });

        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, ProfileSpecialistActivity.class));
            }
        });

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentUser != null){
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(HomePageHealthSpecialist.this, MainActivity.class));
                    finish();
                }
            }
        });

        denonciationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, DenonciationAdmActivity.class));
            }
        });

        quizBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, QuizAdmActivity.class));
            }
        });

        periodCalculatorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, CyclesEngineActivity.class));
            }
        });

    }
}