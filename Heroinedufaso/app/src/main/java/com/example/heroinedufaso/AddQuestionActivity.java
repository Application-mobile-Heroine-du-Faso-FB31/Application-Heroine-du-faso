package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddQuestionActivity extends AppCompatActivity {

    private EditText question, optionA, optionB, optionC, correctAnswer;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);


        question = findViewById(R.id.edtQuestionTxtAddQ);
        optionA = findViewById(R.id.edtOptionA);
        optionB = findViewById(R.id.edtOptionB);
        optionC = findViewById(R.id.edtOptionC);
        correctAnswer = findViewById(R.id.correctAnsNumber);
        add = findViewById(R.id.addQuestionAddQuestionActBtn);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(question.getText()) ||
                        TextUtils.isEmpty(optionA.getText()) ||
                        TextUtils.isEmpty(optionB.getText()) ||
                        TextUtils.isEmpty(optionC.getText()) ||
                        TextUtils.isEmpty(correctAnswer.getText()) ){

                    Toast.makeText(AddQuestionActivity.this, "S'il vous plait veuillez entrer toutes les informations",
                            Toast.LENGTH_SHORT).show();
                }  else{
                    int answer = Integer.parseInt(String.valueOf(correctAnswer.getText()));

                    Question newQuestion = new Question(
                            question.getText().toString(),
                            optionA.getText().toString(),
                            optionB.getText().toString(),
                            optionC.getText().toString(),
                            answer
                    );

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("questions");

                    reference.push().setValue(newQuestion);

                    question.setText("");
                    optionA.setText("");
                    optionB.setText("");
                    optionC.setText("");
                    correctAnswer.setText("");

                    Toast.makeText(AddQuestionActivity.this, "La question a bien été ajouté dans la liste de questions",
                            Toast.LENGTH_SHORT).show();

                }
            }
        });


    }

}