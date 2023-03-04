//***************************************************************************************/
//        *    Title: Firebase Authentication with Phone Number OTP in Android
//        *    Author: Geek for geek
//        *    Date: 2020
//        *    Code version: 1.0
//        *    Availability: https://www.geeksforgeeks.org/firebase-authentication-with-phone-number-otp-in-android/amp/
//        *
//        ***************************************************************************************/

package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class LoginPageUser extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    // variable for our text input

    // field for phone and OTP.

    private EditText edtPhone, edtOTP;
    private TextView  header, message;

    // buttons for generating OTP and verifying OTP

    private Button verifyOTPBtn, generateOTPBtn;



    // string for storing our verification ID

    private String verificationId;

    private final String COUNTRY_CODE = "+1";

    private String headerVerification, headerLogin, messageVerification, messageLogin;

    private TextView resendCode;

    private TextToSpeech textToSpeech;

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_user);



        // initializing variables for button and Edittext.

        edtPhone = findViewById(R.id.idEdtPhoneNumber);

        edtOTP = findViewById(R.id.idEdtOtp);

        verifyOTPBtn = findViewById(R.id.idBtnVerify);

        generateOTPBtn = findViewById(R.id.idBtnGetOtp);

        header = findViewById(R.id.header_txt_view_login_user);

        message = findViewById(R.id.message_txt_view_login_user);

        resendCode = findViewById(R.id.rend_send_verification_code_txt_view);

        headerLogin = "Connexion";
        headerVerification = "Vérification";
        messageLogin = "S'il vous plaît, veuillez entrez votre numéro de téléphone " +
                "pour recevoir un code de vérification. ";

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if(i != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.FRENCH);
                }
            }
        });






        // setting onclick listener for generate OTP button.

        generateOTPBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // below line is for checking whether the user

                // has entered his mobile number or not.

                if (TextUtils.isEmpty(edtPhone.getText().toString())) {

                    // when mobile number text field is empty

                    // displaying a toast message.

                    Toast.makeText(LoginPageUser.this, "SVP Entrez un numéro de telephone valide", Toast.LENGTH_SHORT).show();

                } else {

                    // if the text field is not empty we are calling our

                    // send OTP method for getting OTP from Firebase.

                    String phone = COUNTRY_CODE + edtPhone.getText().toString();

                    sendVerificationCode(phone);

                    messageVerification = "S'il vous plaît, veuillez entrer " +
                            "le code de vérification envoyé au numéro " + phone;

                    header.setText(headerVerification);
                    message.setText(messageVerification);

                    edtPhone.setVisibility(View.INVISIBLE);
                    generateOTPBtn.setVisibility(View.INVISIBLE);

                    edtOTP.setVisibility(View.VISIBLE);
                    verifyOTPBtn.setVisibility(View.VISIBLE);
                    resendCode.setPaintFlags(resendCode.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
                    resendCode.setVisibility(View.VISIBLE);

                    textToSpeech.speak(messageVerification, TextToSpeech.QUEUE_FLUSH, null);

                }

            }

        });

        // initializing on click listener

        // for verify otp button

        verifyOTPBtn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                // validating if the OTP text field is empty or not.

                if (TextUtils.isEmpty(edtOTP.getText().toString())) {

                    // if the OTP text field is empty display

                    // a message to user to enter OTP

                    Toast.makeText(LoginPageUser.this, "SVP Entrer le code de verificiation", Toast.LENGTH_SHORT).show();

                } else {

                    // if OTP field is not empty calling

                    // method to verify the OTP.

                    verifyCode(edtOTP.getText().toString());

//                    String messageLogin = "Afin de pouvoir compléter votre inscription s'il vous plaît " +
//                            "veuillez fournir votre nom complet, " +
//                            "ville de résidence et date de naissance. Si vous n'est pas capable de l'écrire, " +
//                            "veuillez fournir ses informations vocalement à l'aide du buton vocale. ";
//
//                    textToSpeech.speak(messageLogin, TextToSpeech.QUEUE_FLUSH, null);

                }

            }

        });

        resendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = COUNTRY_CODE + edtPhone.getText().toString();
                sendVerificationCode(phone);
            }
        });



    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        // inside this method we are checking if

        // the code entered is correct or not.

        mAuth.signInWithCredential(credential)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            // if the code is correct and the task is succ/essful

                            // we are sending our user to new activity.

                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {

                            // if the code is not correct then we are

                            // displaying an error message to the user.

                            Toast.makeText(LoginPageUser.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                });

    }

    private void updateUI(FirebaseUser user) {

        Intent i = new Intent(LoginPageUser.this, SignUpPage.class);
        i.putExtra("role", "user");
        startActivity(i);
        edtOTP.setText("");
        edtPhone.setText("");
        finish();
    }


    private void sendVerificationCode(String number) {

        // this method is used for getting

        // OTP on user phone number.

        PhoneAuthOptions options =

                PhoneAuthOptions.newBuilder(mAuth)

                        .setPhoneNumber(number)            // Phone number to verify

                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit

                        .setActivity(this)                 // Activity (for callback binding)

                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks

                        .build();

        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    // callback method is called on Phone auth provider.

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks



            // initializing our callbacks for on

            // verification callback method.

            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {


        // below method is used when

        // OTP is sent from Firebase

        @Override

        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

            super.onCodeSent(s, forceResendingToken);

            // when we receive the OTP it

            // contains a unique id which

            // we are storing in our string

            // which we have already created.

            verificationId = s;

        }


        // this method is called when user

        // receive OTP from Firebase.

        @Override

        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            // below line is used for getting OTP code

            // which is sent in phone auth credentials.

            final String code = phoneAuthCredential.getSmsCode();



            // checking if the code

            // is null or not.

            if (code != null) {

                // if the code is not null then

                // we are setting that code to

                // our OTP edittext field.

                edtOTP.setText(code);



                // after setting this code

                // to OTP edittext field we

                // are calling our verifycode method.

                verifyCode(code);

            }

        }


        // this method is called when firebase doesn't

        // sends our OTP code due to any error or issue.

        @Override

        public void onVerificationFailed(FirebaseException e) {

            // displaying error message with firebase exception.

            Toast.makeText(LoginPageUser.this, e.getMessage(), Toast.LENGTH_LONG).show();

        }

    };


    // below method is use to verify code from Firebase.

    private void verifyCode(String code) {

        // below line is used for getting
        // credentials from our verification id and code.

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.

        signInWithCredential(credential);

    }
}

