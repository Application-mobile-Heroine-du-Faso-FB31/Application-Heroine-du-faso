package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView questions;
    private Button option1, option2, option3 , choose, exit;

    private List<Question> questionList;

    private int questNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        questions=findViewById(R.id.textView8);

        option1=findViewById(R.id.Option1);
        option2=findViewById(R.id.Option2);
        option3=findViewById(R.id.Option3);

        choose=findViewById(R.id.buttonChoose);
        exit=findViewById(R.id.buttonExit);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        choose.setOnClickListener(this);exit.setOnClickListener(this);

        getQuestionsList();
    }

    private void getQuestionsList(){
        questionList=new ArrayList<>();
        questionList.add(new Question("Question 1","Option A","Option B","Option C",1,2,3));
        questionList.add(new Question("Question 2","Option C","Option B","Option A",3,2,3));
        questionList.add(new Question("Question 3","Option B","Option A","Option C",2,2,3));
        questionList.add(new Question("Question 4","Option A","Option C","Option B",1,2,3));
        questionList.add(new Question("Question 5","Option C","Option A","Option B",3,2,3));

        setQuestion();
    }

    private void setQuestion(){
        questions.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());

        questNum=0;
    }

    @Override
    public void onClick(View view) {
        int selectOption =0;

        switch (view.getId()){
            case R.id.Option1:
                selectOption=1;
                break;
            case R.id.Option2:
                selectOption=2;
                break;
            case R.id.Option3:
                selectOption=3;
                break;
            default:
        }

        checkAnswer(selectOption,view);
    }

    private void checkAnswer(int selectOption,View view){
        if (selectOption==questionList.get(questNum).getCorrectAns()){
            //Right Answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

        }

        else{
            //wrong answer
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (questionList.get(questNum).getCorrectAns()){
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));

                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            }
        }
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        },2000);

    }

    private void changeQuestion(){
        if (questNum<questionList.size()-1) {
            questNum++;
            playAnim(questions,0,0);
            playAnim(option1,0,0);
            playAnim(option2,0,0);
            playAnim(option3,0,0);

        } else{
            //go to score activity
            //Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
            //startActivity(intent);
            //QuestionsActivity.this.finish();
        }
    }

    private void playAnim(View view,final int value,int viewNum){
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(@NonNull Animator animator) {
                        if (value==0){
                            switch (viewNum)
                            {
                                case 0:
                                    ((TextView)view).setText(questionList.get(questNum).getQuestion());
                                    break;

                                case 1:
                                    ((Button)view ).setText(questionList.get(questNum).getOptionA());
                                    break;

                                case 2 :
                                    ((Button)view ).setText(questionList.get(questNum).getOptionB());
                                    break;

                                case 3 :
                                    ((Button)view ).setText(questionList.get(questNum).getOptionC());
                                    break;
                            }
                            if ((viewNum!=0))
                                ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("@color/pink_300")));

                            playAnim(view,1,viewNum);

                        }
                    }

                    @Override
                    public void onAnimationCancel(@NonNull Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(@NonNull Animator animator) {

                    }
                });

        // private void setText(String optionA) {
        //})



    }

}