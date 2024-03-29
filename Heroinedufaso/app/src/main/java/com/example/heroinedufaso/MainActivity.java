package com.example.heroinedufaso;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private Button adminBtn;
    private Button healthSpecialistBtn;
    private Button userBtn;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser firebaseCurrentUser = mAuth.getCurrentUser();
    private TextToSpeech textToSpeech;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminBtn = (Button) findViewById(R.id.admin_btn_home_page);
        healthSpecialistBtn = (Button) findViewById(R.id.sp_health_btn_home_page);
        userBtn = (Button) findViewById(R.id.user_btn_home_page);


        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.FRENCH);
                }
            }
        });


        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLoginPage.class));
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, LoginPageUser.class));
            }
        });

        healthSpecialistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLoginPage.class).putExtra("selected_role", "health_specialist"));
            }
        });


    }
}