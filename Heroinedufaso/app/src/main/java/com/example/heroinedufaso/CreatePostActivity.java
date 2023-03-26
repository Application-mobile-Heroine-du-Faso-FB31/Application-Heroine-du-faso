package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatePostActivity extends AppCompatActivity {

    private Button cancel, post;
    private CheckBox checkBox;
    private EditText edtPost;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseUser user;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        cancel =findViewById(R.id.cancelPostBtn);
        post = findViewById(R.id.postBtnCreatePost);
        checkBox = findViewById(R.id.anonymeCheckBox);
        edtPost = findViewById(R.id.editTextPost);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();

        getCurrentUser();





        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToDenonciationActivity();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edtPost.getText())){
                    Toast.makeText(CreatePostActivity.this,
                            "S'il vous plaît, veuillez écrire votre dénonciation.", Toast.LENGTH_LONG).show();
                }else{
                    DatabaseReference databaseReference = firebaseDatabase.getReference("post");

                    if(checkBox.isChecked()){

                        Post userPost = new Post(edtPost.getText().toString(), "Anonyme", currentUser.getFullName());
                        databaseReference.push().setValue(userPost);
                    }else{
                        Post userPost = new Post(edtPost.getText().toString(), currentUser.getFullName(), currentUser.getFullName());
                        databaseReference.push().setValue(userPost);
                    }

                    edtPost.setText("");
                    Toast.makeText(CreatePostActivity.this,
                            "Votre dénociation a été publié.", Toast.LENGTH_LONG).show();
                    backToDenonciationActivity();

                }
            }
        });



    }

    public void getCurrentUser(){

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").
                child(user.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                currentUser = dataSnapshot.getValue(User.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });
    }

    private void backToDenonciationActivity(){
        edtPost.setText("");
        startActivity(new Intent(CreatePostActivity.this, DenociationActivity.class));
        finish();
    }
}