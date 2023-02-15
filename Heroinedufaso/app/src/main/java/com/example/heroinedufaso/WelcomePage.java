package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class WelcomePage extends AppCompatActivity {

    private TextView welcomeMessage;
    private String welcomeMsgStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        welcomeMessage = this.<TextView>findViewById(R.id.textView_Welcome_Page);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            welcomeMsgStr = "Bonjour " + b.getString("fullname");
        }

        welcomeMessage.setText(welcomeMsgStr);
    }
}