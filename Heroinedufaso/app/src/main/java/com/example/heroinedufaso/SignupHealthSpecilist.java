package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupHealthSpecilist extends AppCompatActivity {

    private EditText fullName, city, dayOfMonthBirthDay, monthBirthday, yearBirthday,speciality, phoneNumber, email, password, confirmPassword;
    private Button signBtn;

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

    public boolean isValidYear(String year){
        if(year.length() != 4){
            return false;
        }
        try {
            Integer.parseInt(year);
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
            return false;
        }

        return true;
    }

    public boolean isValidMonthDay(String year){
        if(year.length() != 2){
            return false;
        }
        try {
            Integer.parseInt(year);
        }catch (Exception e){
            Log.i(TAG, e.getMessage());
            return false;
        }

        return true;
    }


}