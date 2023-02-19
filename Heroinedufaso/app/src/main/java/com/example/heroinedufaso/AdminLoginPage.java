package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminLoginPage extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBtn;

    private FirebaseAuth mAuth;


    private ArrayList<Manager> managerList = new ArrayList<>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    private List<User> users = new ArrayList<>();

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

                for (DataSnapshot dataSnapshot: snapshot.getChildren())
                {
//                    Log.i(TAG, "datasnapshot : " + dataSnapshot.getValue().toString());

                    User user = dataSnapshot.getValue(User.class);

//                    Log.i(TAG, "user fullname : " + user.getFullName());
                    users.add(user);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        if(databaseReference != null){
            Intent i = new Intent(AdminLoginPage.this, HomePageUser.class);
            startActivity(i);
            finish();
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        emailInput = (EditText) findViewById(R.id.email_input_admin_login_page);
        passwordInput = (EditText) findViewById(R.id.password_input_admin_login_page);
        loginBtn = (Button) findViewById(R.id.login_btn_admin_login_page);




        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users");




        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                login(email, password);
            }
        });






    }

    public void login(String email, String password){
//
//        Log.i(TAG, "login: email " + email);
//        Log.i(TAG, "login: password " + password);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AdminLoginPage.this, "Echec de connexion.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void updateUI(FirebaseUser user) {

                       if(!users.isEmpty()) {
                           if(user.getUid().equals(users.get(0).getUid())){
                               Intent i = new Intent(AdminLoginPage.this, HomePageUser.class);
                               startActivity(i);
                               finish();
                           }
                       } else{
                           Intent i = new Intent(AdminLoginPage.this, SignUpPage.class);
                           i.putExtra("role", "manager");
                           startActivity(i);
                           finish();
                       }



                    }
                });
    }

}