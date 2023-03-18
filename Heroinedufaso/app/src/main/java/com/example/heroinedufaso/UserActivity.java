package com.example.heroinedufaso;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.animation.AnimationUtils;

public class UserActivity extends AppCompatActivity {

    private TextView AppName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        AppName=findViewById(R.id.AppName);


        Animation anim= AnimationUtils.loadAnimation(this,R.anim.myanim);
        AppName.setAnimation(anim);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

                Intent intent =new Intent (UserActivity.this,MainActivity.class);
                startActivity(intent);
            }
        }).start();

    }




}
