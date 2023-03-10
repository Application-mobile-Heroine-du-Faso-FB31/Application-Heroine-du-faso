//***************************************************************************************/
//        *    Title: Date Picker Dialog on Android
//        *    Author: Mitch
//        *    Date: 2020
//        *    Code version: 1.0
//        *    Availability: https://www.youtube.com/watch?v=AdTzD96AhE0
//        *
//        ***************************************************************************************/
package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Locale;

public class SignUpPage extends AppCompatActivity  implements
        DatePickerDialog.OnDateSetListener {

    private EditText fullName, city;
    private Button birthday, voice, validate;

    private String birthdayInput;
    private String role;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private TextToSpeech textToSpeech;

    private TextView birthdayOutput;



    @Override
    protected void onStart() {
        super.onStart();
    }



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);


        fullName = findViewById(R.id.Nom_page_creation);
        city = findViewById(R.id.ville_page_creation);
        birthday = findViewById(R.id.birthday_input_page_creation);
        voice = findViewById(R.id.Vocal_btn_page_creation);
        validate = findViewById(R.id.Valider_btn_page_creation);
        birthdayOutput = findViewById(R.id.birtdayTxtViewSignUP);




        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            role = b.getString("role");
        }

        FirebaseUser currentUser = mAuth.getCurrentUser();

//        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//                if(i != TextToSpeech.ERROR) {
//                    textToSpeech.setLanguage(Locale.FRENCH);
//                }
//            }
//        });


        birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(fullName.getText().toString().trim())
                && !TextUtils.isEmpty(city.getText().toString().trim())
                && birthdayInput != null){

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("users").
                            child(currentUser.getUid());

                    FirebaseUser currentUser = mAuth.getCurrentUser();

                    User user = new User(
                            birthdayInput,
                            city.getText().toString().trim(),
                            fullName.getText().toString(),
                            currentUser.getPhoneNumber(),
                            role,
                            currentUser.getUid()
                    );

                    myRef.setValue(user);

                    if(role.equals("manager")){
                        startActivity(new Intent(SignUpPage.this, AdminHomePage.class));
                        finish();
                    }else if(role.equals("user")){
                        startActivity(new Intent(SignUpPage.this, HomePageUser.class));
                        finish();
                    }else{
                        startActivity(new Intent(SignUpPage.this, HomePageHealthSpecialist.class));
                        finish();
                    }

                }
                else{
                    Toast.makeText(SignUpPage.this,
                            "Information incomplete", Toast.LENGTH_LONG).show();
                }

                fullName.setText("");
                city.setText("");
                birthday.setText("Date de naissance");
            }
        });


//        getVoiceInstruction();
        checkData();

//        if(!hasData){
//            textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//                @Override
//                public void onInit(int status) {
//                    if (status != TextToSpeech.ERROR) {
//                        // replace this Locale with whatever you want
//                        String messageLogin = "Afin de pouvoir compléter votre inscription s'il vous plaît " +
//                                "veuillez fournir votre nom complet, " +
//                                "ville de résidence et date de naissance. Si vous n'est pas capable de l'écrire, " +
//                                "veuillez fournir ses informations vocalement à l'aide du buton vocale. ";
//
//                        textToSpeech.speak(messageLogin, TextToSpeech.QUEUE_FLUSH, null);
//
//                    }
//                }
//            });
//        }






    }

//    private void getVoiceInstruction() {
//        String messageLogin = "Afin de pouvoir compléter votre inscription s'il vous plaît " +
//                "veuillez fournir votre nom complet, " +
//                "ville et date. Si vous n'est pas capable de l'écrire, " +
//                "veuillez fournir ses informations vocalement à l'aide du buton vocale. ";
//
//        textToSpeech.speak(messageLogin, TextToSpeech.QUEUE_FLUSH, null);
//    }

    private boolean checkData() {
        FirebaseUser mAuthCurrentUser = mAuth.getCurrentUser();
        boolean [] hasData = {false};
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Chargement ...");
        progressDialog.show();


        if(mAuthCurrentUser != null){
            final FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ref = database.getReference("users").
                    child(mAuthCurrentUser.getUid());

            ref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    User user = snapshot.getValue(User.class);
                    if(user != null){

                        if(user.getUid().equals(mAuthCurrentUser.getUid())){
                            hasData[0] = true;

                            if(user.getRole().equals("user")){
                                progressDialog.dismiss();
                                startActivity(new Intent(SignUpPage.this, HomePageUser.class));
                                finish();
                            } else if (user.getRole().equals("manager")) {
                                progressDialog.dismiss();
                                startActivity(new Intent(SignUpPage.this, AdminHomePage.class));
                                finish();
                            }



                            return;

                        }

                    }else{
//                        new Handler().postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                dialog.dismiss();
//                            }
//                        }, 2000);
                        progressDialog.dismiss();
                        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
                            @Override
                            public void onInit(int status) {
                                if (status != TextToSpeech.ERROR) {
                                    // replace this Locale with whatever you want
                                    String messageLogin = "Afin de pouvoir compléter votre inscription s'il vous plaît " +
                                            "veuillez fournir votre nom complet, " +
                                            "ville de résidence et date de naissance. Si vous n'est pas capable de l'écrire, " +
                                            "veuillez fournir ses informations vocalement à l'aide du bouton vocale. ";

                                    textToSpeech.setLanguage(Locale.FRENCH);

                                    textToSpeech.speak(messageLogin, TextToSpeech.QUEUE_FLUSH, null);

                                }
                            }
                        });

                        return;
                    }
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            dialog.dismiss();
//                        }
//                    }, 2000);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                    Log.i(TAG,"The read failed: " + error.getCode());
                }
            });



        }

        return hasData[0];
    }

    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        birthdayInput  = dayOfMonth + "/" + month + "/" + year;
        birthday.setText(birthdayInput);
        birthdayOutput.setText("Date de naissance " + birthdayInput);
    }

}