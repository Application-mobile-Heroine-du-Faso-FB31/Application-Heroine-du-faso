package com.example.heroinedufaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UserManagementActivity extends AppCompatActivity {

    private TextView txtFullName, txtPhoneNumber, txtCity, txtBirthday, txtRole;
    private Button deleteAccount, disableEnableAccount;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_management);

        txtFullName = findViewById(R.id.fullname_profile_management);
        txtPhoneNumber = findViewById(R.id.phone_number_txtV_profile_management);
        txtCity = findViewById(R.id.city_txtV_profile_management);
        txtBirthday = findViewById(R.id.birthday_txtV_profile_management);
        txtRole = findViewById(R.id.role_txtV_profile_management);
        deleteAccount = findViewById(R.id.delete_profile_btn);
        disableEnableAccount = findViewById(R.id.disable_profile_btn);
        profile = findViewById(R.id.profile_image_profile_activity_management);


        setInfo();
    }

    private void setInfo(){

        ProgressDialog progressDialog = new ProgressDialog(UserManagementActivity.this);
        progressDialog.show();

        Glide.with(getApplicationContext()).load(getIntent().getStringExtra("img_of_the_user")).error(R.drawable.baseline_person_24)
                .placeholder(R.drawable.baseline_person_24).into(profile);

        txtFullName.setText(getIntent().getStringExtra("full_name_of_the_user"));
        txtPhoneNumber.setText(getIntent().getStringExtra("phone_number"));
        txtRole.setText(getIntent().getStringExtra("role"));
        txtCity.setText(getIntent().getStringExtra("city"));
        txtBirthday.setText(getIntent().getStringExtra("birthday"));

        progressDialog.dismiss();
    }
}