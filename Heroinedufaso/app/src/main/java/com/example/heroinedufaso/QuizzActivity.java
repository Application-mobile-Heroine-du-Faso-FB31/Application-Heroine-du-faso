package com.example.heroinedufaso;

import static java.lang.Thread.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class QuizzActivity extends AppCompatActivity {

    private TextView title;
    private Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizz);

        title=findViewById(R.id.q_title);
        start=findViewById(R.id.q_Button);

        Animation anim= AnimationUtils.loadAnimation(this,R.anim.myanim);
        title.setAnimation(anim);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuizzActivity.this,QuestionsActivity.class);
                startActivity(intent);

            }
        });

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