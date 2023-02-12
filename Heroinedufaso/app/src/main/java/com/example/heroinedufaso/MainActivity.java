package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button adminBtn;
    private Button healthSpecialistBtn;
    private Button userBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adminBtn = (Button) findViewById(R.id.admin_btn_home_page);
        healthSpecialistBtn = (Button) findViewById(R.id.sp_health_btn_home_page);
        userBtn = (Button) findViewById(R.id.user_btn_home_page);


        adminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, AdminLoginPage.class));
            }
        });

        userBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginPageUser.class));
            }
        });
    }
}