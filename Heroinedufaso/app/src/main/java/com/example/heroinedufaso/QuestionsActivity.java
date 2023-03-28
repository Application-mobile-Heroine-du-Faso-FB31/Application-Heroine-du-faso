package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity {
    private TextView questions,qCount,timer;
    private Button option1, option2, option3 , exit;

    private ArrayList<Question> questionList;

    private CountDownTimer countDown;

    private int questNum;

    private int score;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        questions=findViewById(R.id.questionText);
        qCount=findViewById(R.id.quest_num);


        option1=findViewById(R.id.Option1);
        option2=findViewById(R.id.Option2);
        option3=findViewById(R.id.Option3);

        exit=findViewById(R.id.buttonExit);
        questionList=new ArrayList<>();
        questNum = 1;
        score = 0;

//        option1.setOnClickListener(this);
//        option2.setOnClickListener(this);
//        option3.setOnClickListener(this);






        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(QuestionsActivity.this, HomePageUser.class));
                finish();
            }
        });

        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questNum - 1 < questionList.size()-1){
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        score++;

                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    questNum++;

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showQuestion(questNum-1);
                    }
                    },2000);

                }else{
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        score++;

                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
                            intent.putExtra("PointsTotaux", String.valueOf(score)+"/"+String.valueOf(questionList.size()));
                            startActivity(intent);
                            finish();

                        }
                    },2000);
                }
            }
        });


        option2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questNum - 1 < questionList.size()-1){
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        score++;
                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }
                    questNum++;

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestion(questNum-1);
                        }
                    },2000);

                }else{
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        score++;
                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
                            intent.putExtra("PointsTotaux", String.valueOf(score)+"/"+String.valueOf(questionList.size()));
                            startActivity(intent);
                            finish();

                        }
                    },2000);
                }
            }
        });

        option3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questNum - 1 < questionList.size()-1){
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        score++;
                    }
                    questNum++;

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            showQuestion(questNum-1);
                        }
                    },2000);

                }else{
                    if(questionList.get(questNum-1).getCorrectAns() == 1){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));


                    }else if(questionList.get(questNum-1).getCorrectAns() == 2){
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

                    }else{
                        option1.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                        score++;
                    }

                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
                            intent.putExtra("PointsTotaux", String.valueOf(score)+"/"+String.valueOf(questionList.size()));
                            startActivity(intent);
                            finish();

                        }
                    },2000);
                }
            }
        });


        getQuestionsList();


    }

    private void showQuestion(int pos) {
        qCount.setText("Question " + questNum);
        option1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
        option2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
        option3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
        questions.setText(questionList.get(pos).getQuestion());
        option1.setText(questionList.get(pos).getOptionA());
        option2.setText(questionList.get(pos).getOptionB());
        option3.setText(questionList.get(pos).getOptionC());

    }

    private void loading(){
        CustomProgressDialog dialog =  new CustomProgressDialog(QuestionsActivity.this);
        dialog.show();

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
            }
        },2000);


    }

    private void getQuestionsList(){



        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("questions");

        loading();
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Question question = dataSnapshot.getValue(Question.class);
                    questionList.add(question);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        questionList.add(new Question("Qu'est-ce que la menstruation ?","La période de la vie où une femme peut tomber enceinte","Le moment où une femme ovule","Le moment où une femme saigne de l'utérus",3));
        questionList.add(new Question("Qu'est-ce que le cancer du sein ?","Une maladie qui affecte le système digestif","Une maladie qui affecte le système reproducteur féminin","Une maladie qui affecte les glandes mammaires",3));
        questionList.add(new Question("Qu'est-ce que la contraception ?","Une méthode pour augmenter les chances de tomber enceinte","Une méthode pour réduire les chances de tomber enceinte","Une méthode pour arrêter les menstruations",2));
        questionList.add(new Question("Qu'est-ce que l'endométriose ?","Une maladie qui affecte les organes reproducteurs féminins","Une maladie qui affecte les voies urinaires","Une maladie qui affecte les poumons",1));


        showQuestion(0);

//        setQuestion();
    }

//    private void setQuestion(){
//        timer.setText(String.valueOf(10));
//        questions.setText(questionList.get(0).getQuestion());
//        option1.setText(questionList.get(0).getOptionA());
//        option2.setText(questionList.get(0).getOptionB());
//        option3.setText(questionList.get(0).getOptionC());
//
//        qCount.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));
//        startTimer();
//
//        questNum=0;
//
//    }
//
//    private void startTimer() {
//        CountDownTimer countDown=new CountDownTimer(12000,1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                if (millisUntilFinished<10){
//                    timer.setText(String.valueOf(millisUntilFinished/1000));
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                changeQuestion();
//
//            }
//        };
//        countDown.start();
//    }
//
//    private void changeQuestion(){
//        if (questNum<questionList.size()-1)
//        {
//            questNum++;
//            playAnim(questions,0,0);
//            playAnim(option1,0,1);
//            playAnim(option2,0,2);
//            playAnim(option3,0,3);
//
//            qCount.setText(String.valueOf(questNum+1)+"/"+String.valueOf(questionList.size()));
//            timer.setText(String.valueOf(10));
//            startTimer();
//
//
//
//        } else{
//            //go to score activity
//            Intent intent=new Intent(QuestionsActivity.this,ScoreActivity.class);
//            intent.putExtra("PointsTotaux", String.valueOf(score)+"/"+String.valueOf(questionList.size()));
//            startActivity(intent);
//            QuestionsActivity.this.finish();
//            startTimer();
//        }
//    }
//
//    private void playAnim(View view,final int value,int viewNum){
//        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
//                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
//                .setListener(new Animator.AnimatorListener() {
//                    @Override
//                    public void onAnimationStart(@NonNull Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationEnd(@NonNull Animator animator) {
//                        if (value==0){
//                            switch (viewNum)
//                            {
//                                case 0:
//                                    ((TextView)view).setText(questionList.get(questNum).getQuestion());
//                                    break;
//
//                                case 1:
//                                    ((Button)view ).setText(questionList.get(questNum).getOptionA());
//                                    break;
//
//                                case 2 :
//                                    ((Button)view ).setText(questionList.get(questNum).getOptionB());
//                                    break;
//
//                                case 3 :
//                                    ((Button)view ).setText(questionList.get(questNum).getOptionC());
//                                    break;
//                            }
////                           if (viewNum!=0)
////                               ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("B5627E")));
//
//
//
//                            playAnim(view,1,viewNum);
//
//                        }
//                    }
//
//                    @Override
//                    public void onAnimationCancel(@NonNull Animator animator) {
//
//                    }
//
//                    @Override
//                    public void onAnimationRepeat(@NonNull Animator animator) {
//
//                    }
//                });
//
//        // private void setText(String optionA) {
//        //})
//
//
//
//    }
//    @Override
//    public void onClick(View view) {
//        int selectOption =0;
//
//        switch (view.getId())
//        {
//            case R.id.Option1:
//                selectOption=1;
//                break;
//
//            case R.id.Option2:
//                selectOption=2;
//                break;
//
//            case R.id.Option3:
//                selectOption=3;
//                break;
//
//            default:
//        }
//        checkAnswer(selectOption,view);
//    }
//
//    private void checkAnswer(int selectOption,View view){
//        if (selectOption==questionList.get(questNum).getCorrectAns()){
//            //Right Answer
//            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            score++;
//
//        }
//
//        else{
//            //wrong answer
//            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
//            switch (questionList.get(questNum).getCorrectAns()){
//                case 1:
//                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//
//                case 2:
//                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//
//                case 3:
//                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
//            }
//        }
//        Handler handler=new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                option1.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
//                option2.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
//                option3.setBackgroundTintList(ColorStateList.valueOf(Color.rgb(181,98,126)));
//                changeQuestion();
//            }
//        },2000);
//
//    }
//
//
//
//
//
//


}