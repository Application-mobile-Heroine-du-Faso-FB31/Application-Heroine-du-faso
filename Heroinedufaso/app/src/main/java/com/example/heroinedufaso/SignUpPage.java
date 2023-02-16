package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Calendar;
import java.util.Date;

public class SignUpPage extends AppCompatActivity  implements
        DatePickerDialog.OnDateSetListener {

    private EditText fullName, city;
    private Button birthday, voice, validate;

    private String birthdayInput;
    private String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        fullName = findViewById(R.id.Nom_page_creation);
        city = findViewById(R.id.ville_page_creation);
        birthday = findViewById(R.id.birthday_input_page_creation);
        voice = findViewById(R.id.Vocal_btn_page_creation);
        validate = findViewById(R.id.Valider_btn_page_creation);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            role = b.getString("role");
        }



        DAO dao = new DAO();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in
            user.reload();
            Log.i(TAG, user.getUid() + " is signed in");

        } else {
            // No user is signed in
            Log.i(TAG, "No user is signed in");
        }

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
                        Intent i = new Intent(SignUpPage.this, HomePageUser.class);
                        startActivity(i);
                        finish();
                }
                else{
                    Toast.makeText(SignUpPage.this,
                            "Information incomplete", Toast.LENGTH_LONG).show();
                }
            }
        });



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
        String date = month + "/" + dayOfMonth + "/" + year;

        birthdayInput = date;
    }

}