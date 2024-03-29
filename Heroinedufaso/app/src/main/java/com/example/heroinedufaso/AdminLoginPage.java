package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.regex.Pattern;

public class AdminLoginPage extends AppCompatActivity {

    private EditText emailInput;
    private EditText passwordInput;
    private Button loginBtn;
    private TextView linkSignup;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private boolean enableLink = false;

    @Override
    protected void onStart() {
        super.onStart();
    }


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_page);

        emailInput = (EditText) findViewById(R.id.email_input_admin_login_page);
        passwordInput = (EditText) findViewById(R.id.password_input_admin_login_page);
        loginBtn = (Button) findViewById(R.id.login_btn_admin_login_page);
        linkSignup = findViewById(R.id.signup_link_adm_login_activity);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();

        if(b!=null){
            if(b.getString("selected_role").equals("health_specialist")){
                enableLink = true;
            }
        }

        if(enableLink){
            linkSignup.setVisibility(View.VISIBLE);
        }

        linkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminLoginPage.this, SignupHealthSpecilist.class));
            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    Toast.makeText(AdminLoginPage.this, "Veuillez entrer votre adresse email.",
                            Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(AdminLoginPage.this, "Veuillez entrer votre mot de passe.",
                            Toast.LENGTH_SHORT).show();
                } else if(!isValidEmail(email)){
                    Toast.makeText(AdminLoginPage.this, "Adresse email invalid.",
                            Toast.LENGTH_SHORT).show();
                }else{
                    login(email, password);
                }

            }
        });

    }

    private static boolean isValidEmail(String email)
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

    public void login(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if(enableLink){
                                startActivity(new Intent(AdminLoginPage.this, HomePageHealthSpecialist.class));
                                finish();
                            }else{
                                updateUI(user);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(AdminLoginPage.this, "Echec de connexion.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                    private void updateUI(FirebaseUser user) {
                        Intent i = new Intent(AdminLoginPage.this, SignUpPage.class);
                        i.putExtra("role", "manager");
                        startActivity(i);
                        finish();
                    }
                });
    }


}