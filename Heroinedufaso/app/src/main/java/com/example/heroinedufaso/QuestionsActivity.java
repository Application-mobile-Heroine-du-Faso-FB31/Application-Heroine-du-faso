package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView questions,qCount,timer;
    private Button option1, option2, option3 , exit;

    private List<Question> questionList;

    private CountDownTimer countDown;

    private int questNum;

    private int score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questions=findViewById(R.id.textView8);
        qCount=findViewById(R.id.quest_num);
        timer=findViewById(R.id.countdown);

        option1=findViewById(R.id.Option1);
        option2=findViewById(R.id.Option2);
        option3=findViewById(R.id.Option3);

        exit=findViewById(R.id.buttonExit);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);

        getQuestionsList();

    }

    private void getQuestionsList(){
        questionList=new ArrayList<>();
        questionList.add(new Question("Question 1","Option A","Option B","Option C",2));
        questionList.add(new Question("Question 2","Option A","Option B","Option C",3));
        questionList.add(new Question("Question 3 ","Option A","Option B","Option C",1));
        questionList.add(new Question("Question 4 ","Option A","Option B","Option C",3));
        questionList.add(new Question("Question 5 ","Option A","Option B","Option C",2));
        setQuestion();
    }

    private void setQuestion(){
        timer.setText(String.valueOf(10));
        questions.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());

        qCount.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));
        startTimer();

        questNum=0;

    }

    private void startTimer() {
        CountDownTimer countDown=new CountDownTimer(12000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished<10){
                    timer.setText(String.valueOf(millisUntilFinished/1000));
                }
            }

            @Override
            public void onFinish() {
                changeQuestion();

            }
        };
        countDown.start();
    }

    private void changeQuestion(){
        if (questNum<questionList.size()-1)
        {
            questNum++;
            playAnim(questions,0,0);
            playAnim(option1,0,1);
            playAnim(option2,0,2);
            playAnim(option3,0,3);

            qCount.setText(String.valueOf(questNum+1)+"/"+String.valueOf(questionList.size()));
            timer.setText(String.valueOf(10));
            startTimer();



        } else{
            //go to score activity
            Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
            intent.putExtra("PointsTotaux", String.valueOf(score)+"/"+String.valueOf(questionList.size()));
            startActivity(intent);
            QuestionsActivity.this.finish();
            startTimer();
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
//                           if (viewNum!=0)
//                               ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("B5627E")));



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
    @Override
    public void onClick(View view) {
        int selectOption =0;

        switch (view.getId())
        {
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
            score++;

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
                option1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
                option2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
                option3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
                changeQuestion();
            }
        },2000);

    }








}