package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileUserActivity extends AppCompatActivity {

//    private Button backBtn;
    private TextView fullName, phoneNumber, city, birthday;
    private ImageButton profilePicture;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private DatabaseReference databaseReference;

    private List<User> users = new ArrayList<>();

    @Override
    protected void onStart() {
        super.onStart();



        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            databaseReference = FirebaseDatabase.getInstance().getReference("users")
                    .child(currentUser.getUid());

            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

//                    Log.i(TAG, "on start function android " + currentUser.getUid());

                    for (DataSnapshot dataSnapshot: snapshot.getChildren())
                    {
//                        Log.i(TAG, "datasnapshot : " + dataSnapshot.getValue().toString());

                        User user = dataSnapshot.getValue(User.class);
                        fullName.setText(user.getFullName());
                        city.setText(user.getCity());
                        birthday.setText(user.getBirthday());

                        Log.i(TAG, "user fullname : " + user.getFullName());
                        users.add(user);

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }




    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        FirebaseUser currentUser = mAuth.getCurrentUser();

//        backBtn = findViewById(R.id.back_btn_profile);
        fullName = findViewById(R.id.fullname_profile);
        phoneNumber = findViewById(R.id.phone_number_txtV_profile);
        city = findViewById(R.id.city_txtV_profile);
        birthday = findViewById(R.id.birthday_txtV_profile);

//        backBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(ProfileUserActivity.this, HomePageUser.class);
//                startActivity(i);
//                finish();
//            }
//        });

//        Log.i(TAG, "current user : " + currentUser.getEmail());

        phoneNumber.setText(currentUser.getPhoneNumber());


    }


}