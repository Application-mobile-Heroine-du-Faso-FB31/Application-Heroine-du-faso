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

    private Button periodEngineBtn;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_health_specialist);

        profileBtn = (Button) findViewById(R.id.profile_health_specialist_home_btn);
        signOutBtn = (Button) findViewById(R.id.sign_out_btn);
        chatConsultationBtn = (Button) findViewById(R.id.chat_specialist_consultation);
        //periodEngineBtn = (Button) findViewById(R.id.menstruel);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();


        chatConsultationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePageHealthSpecialist.this, ConsultationSpecialist.class));
            }
        });
    }
}