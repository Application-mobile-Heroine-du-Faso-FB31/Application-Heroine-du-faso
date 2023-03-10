package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupHealthSpecilist extends AppCompatActivity {

    private EditText fullName, city, dayOfMonthBirthDay, monthBirthday, yearBirthday,speciality, phoneNumber, email, password, confirmPassword;
    private Button signBtn;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_health_specilist);

        fullName = findViewById(R.id.fullnamehealthSPSignup);
        city = findViewById(R.id.cityhealthSPSignup);
        dayOfMonthBirthDay = findViewById(R.id.dayOfMonthBirthdaySignHSP);
        monthBirthday = findViewById(R.id.MonthBirthdaySignUpHSP);
        yearBirthday = findViewById(R.id.yearBirthdaySignUpHSP);
        speciality = findViewById(R.id.specialityhealthSPSignup);
        phoneNumber = findViewById(R.id.editTextPhoneSignUPHSP);
        email = findViewById(R.id.editTextTextEmailAddressSignUPHSP);
        password = findViewById(R.id.editTextTextPasswordSignUPHSP);
        confirmPassword = findViewById(R.id.editTextTextPasswordConfirmSHSP);

        signBtn = findViewById(R.id.BtnSignupHSP);

        firebaseAuth = FirebaseAuth.getInstance();



        signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullNameStr = fullName.getText().toString().trim();
                String cityStr = city.getText().toString().trim();
                String dayOfMonthBirthDayStr = dayOfMonthBirthDay.getText().toString().trim();
                String monthBirthdayStr = monthBirthday.getText().toString().trim();
                String yearBirthdayStr = yearBirthday.getText().toString().trim();
                String specialityStr = speciality.getText().toString().trim();
                String emailStr = email.getText().toString().trim();
                String passwordStr = password.getText().toString().trim();
                String confirmPasswordStr = confirmPassword.getText().toString().trim();
                String phoneNumberStr = phoneNumber.getText().toString().trim();

                if(TextUtils.isEmpty(fullNameStr) || TextUtils.isEmpty(cityStr) ||
                  TextUtils.isEmpty(dayOfMonthBirthDayStr) || TextUtils.isEmpty(monthBirthdayStr)
                || TextUtils.isEmpty(yearBirthdayStr) || TextUtils.isEmpty(specialityStr) ||
                TextUtils.isEmpty(emailStr) || TextUtils.isEmpty(passwordStr) || TextUtils.isEmpty(confirmPasswordStr)){
                    Toast.makeText(SignupHealthSpecilist.this, "SVP Entrer toute les informations requise afin de creer votre compte", Toast.LENGTH_SHORT).show();
                }
                else if (!isValidEmail(emailStr)){
                    Toast.makeText(SignupHealthSpecilist.this, "SVP fournir une adresse email valide", Toast.LENGTH_SHORT).show();
                }else if(!isValidPhoneNumber(phoneNumberStr)){
                    Toast.makeText(SignupHealthSpecilist.this, "SVP Entrer fournir un numero de telephone valide", Toast.LENGTH_SHORT).show();
                }else if(!checkPassword(passwordStr, confirmPasswordStr)){
                    Toast.makeText(SignupHealthSpecilist.this, "Le mot de passe doit etre le meme que la confirmation de mot de passe", Toast.LENGTH_SHORT).show();
                }else{
                    firebaseAuth.createUserWithEmailAndPassword(emailStr, passwordStr).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(SignupHealthSpecilist.this, "Votre compte a été crée avec succes", Toast.LENGTH_SHORT).show();
                                fullName.setText("");
                                city.setText("");
                                dayOfMonthBirthDay.setText("");
                                monthBirthday.setText("");
                                yearBirthday.setText("");
                                speciality.setText("");
                                phoneNumber.setText("");
                                email.setText("");
                                password.setText("");
                                confirmPassword.setText("");

                                firebaseAuth.signInWithEmailAndPassword(emailStr, passwordStr);

                                if(FirebaseAuth.getInstance().getCurrentUser() != null){
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    DatabaseReference myRef = database.getReference("healthSpecialist").
                                            child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    String birthday = dayOfMonthBirthDayStr + "/" + monthBirthdayStr + "/" + yearBirthdayStr;

                                    HealthSpecialist healthSpecialist = new HealthSpecialist(
                                            birthday,
                                            cityStr,
                                            emailStr,
                                            fullNameStr,
                                            phoneNumberStr,
                                            "health_specialist",
                                            specialityStr,
                                            FirebaseAuth.getInstance().getCurrentUser().getUid());

                                    myRef.setValue(healthSpecialist);

                                    startActivity(new Intent(SignupHealthSpecilist.this, HomePageHealthSpecialist.class));
                                }



                            }else{
                                Toast.makeText(SignupHealthSpecilist.this, "Echec dans la creation du compte", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    public boolean checkPassword(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }

    public boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber)
    {

        // The given argument to compile() method
        // is regular expression. With the help of
        // regular expression we can validate mobile
        // number.
        // The number should be of 10 digits.

        // Creating a Pattern class object
        Pattern p = Pattern.compile("^\\d{10}$");

        // Pattern class contains matcher() method
        // to find matching between given number
        // and regular expression for which
        // object of Matcher class is created
        Matcher m = p.matcher(phoneNumber);

        // Returning boolean value
        return (m.matches());
    }



}