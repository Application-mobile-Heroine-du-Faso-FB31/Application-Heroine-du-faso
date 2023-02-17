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

import android.content.Intent;
import android.os.Bundle;
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
import java.util.concurrent.TimeUnit;

public class LoginPageUser extends AppCompatActivity {

    private FirebaseAuth mAuth;



    // variable for our text input

    // field for phone and OTP.

    private EditText edtPhone, edtOTP;



    // buttons for generating OTP and verifying OTP

    private Button verifyOTPBtn, generateOTPBtn;



    // string for storing our verification ID

    private String verificationId;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<Person> users = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null &&
//                currentUser.getUid().toString().equals("jA8x1JtbPhRK1ssUQHrQBgAO5l52")
//        ){
//            Intent intent = new Intent(new Intent(AdminLoginPage.this, AdminHomePage.class));
//            startActivity(intent);
//            finish();
//        }



        FirebaseUser currentUser = mAuth.getCurrentUser();



        databaseReference = FirebaseDatabase.getInstance().getReference("users")
                .child(currentUser.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i(TAG, "on start function android " + currentUser.getUid());

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    Log.i(TAG, "datasnapshot : " + dataSnapshot.getValue().toString());

                    Person user = dataSnapshot.getValue(Person.class);

                    Log.i(TAG, "user fullname : " + user.getFullName());
                    users.add(user);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_user);



        mAuth = FirebaseAuth.getInstance();

        // initializing variables for button and Edittext.

        edtPhone = findViewById(R.id.idEdtPhoneNumber);

        edtOTP = findViewById(R.id.idEdtOtp);

        verifyOTPBtn = findViewById(R.id.idBtnVerify);

        generateOTPBtn = findViewById(R.id.idBtnGetOtp);


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

                    String phone = "+1" + edtPhone.getText().toString();

                    sendVerificationCode(phone);

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

                }

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

        if (!users.isEmpty()) {
            if (user.getUid().equals(users.get(0).getUid())) {
                Intent i = new Intent(LoginPageUser.this, HomePageUser.class);
                startActivity(i);
                finish();
            }
        } else {
            Intent i = new Intent(LoginPageUser.this, SignUpPage.class);
            i.putExtra("role", "user");
            startActivity(i);
            finish();
        }

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

