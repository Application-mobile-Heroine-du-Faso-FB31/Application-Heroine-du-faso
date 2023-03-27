package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class QuizAdmActivity extends AppCompatActivity {
    private Button addQuestion, questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_adm);


        addQuestion = findViewById(R.id.addQuestionBtn);
        questionList = findViewById(R.id.questionList);


        addQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizAdmActivity.this, AddQuestionActivity.class));
            }
        });

        questionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuizAdmActivity.this, QuestionListActivity.class));
            }
        });
    }
}