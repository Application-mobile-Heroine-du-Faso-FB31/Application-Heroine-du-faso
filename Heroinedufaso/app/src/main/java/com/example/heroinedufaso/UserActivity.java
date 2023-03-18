package com.example.heroinedufaso;

import static java.lang.Thread.sleep;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class UserActivity extends AppCompatActivity {

    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        title=findViewById(R.id.q_title);



       Animation anim= AnimationUtils.loadAnimation(this,R.anim.myanim);
        title.setAnimation(anim);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }

            }
        }).start();

    }




}
