package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginPageUser extends AppCompatActivity {

    private Button sendVerificationCodeBtn;
//    private Button inscription;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_user);

        sendVerificationCodeBtn = (Button) findViewById(R.id.login_btn_login_page_user);
//        inscription = (Button) findViewById(R.id.inscription_btn_login_page_user);
        phoneNumber = (EditText) findViewById(R.id.phone_number_input_login_page);


//        inscription.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(LoginPageUser.this, SignUpPage.class));
//            }
//        });
    }
}