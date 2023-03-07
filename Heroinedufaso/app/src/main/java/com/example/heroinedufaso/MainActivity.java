package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
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
        if(firebaseCurrentUser != null){
            startActivity(new Intent(MainActivity.this, HomePageUser.class));
        }
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
                String messageLogin = "S'il vous plaît, veuillez entrez votre numéro de téléphone " +
                        "pour recevoir un code de vérification. ";

                textToSpeech.speak(messageLogin, TextToSpeech.QUEUE_FLUSH, null);
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