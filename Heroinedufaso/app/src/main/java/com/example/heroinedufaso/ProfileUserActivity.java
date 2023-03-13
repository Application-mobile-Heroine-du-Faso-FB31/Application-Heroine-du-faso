package com.example.heroinedufaso;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.UUID;

public class ProfileUserActivity extends AppCompatActivity {

    private Button backBtn;
    private EditText fullName, city;
    private TextView phoneNumber, birthday;
    private ImageButton profilePicture;

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private DatabaseReference databaseReference;
    private Button updateProfileInfoBtn;

    private User currentUser = new User();

    private FirebaseUser firebaseCurrentUser;

    private int SELECT_PICTURE = 200;

    private Uri selectedImageUri;

    @Override
    protected void onStart() {
        super.onStart();
    }





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);

        fullName = findViewById(R.id.fullname_profile);
        phoneNumber = findViewById(R.id.phone_number_txtV_profile);
        city = findViewById(R.id.city_txtV_profile);
        birthday = findViewById(R.id.birthday_txtV_profile);
        profilePicture = findViewById(R.id.profile_image_profile_activity);
        updateProfileInfoBtn = findViewById(R.id.update_profile_info_btn);
        backBtn = findViewById(R.id.back_btn_profile);
        firebaseCurrentUser = mAuth.getCurrentUser();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileUserActivity.this, HomePageUser.class));
                finish();
            }
        });


        updateProfileInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!TextUtils.isEmpty(fullName.getText().toString().trim())
                  && !TextUtils.isEmpty(city.getText().toString().trim())){

                    currentUser.setFullName(fullName.getText().toString());
                    currentUser.setCity(city.getText().toString());

                    databaseReference.setValue(currentUser);

                    Toast.makeText(ProfileUserActivity.this, "Vos données personnelles ont bien été modifiées.", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(ProfileUserActivity.this, "Veuillez remplir toutes les sections, s'il vous plaît.", Toast.LENGTH_SHORT).show();
                }

                if(selectedImageUri != null){
                    uploadImage();
                }


            }
        });

        profilePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);

                // pass the constant to compare it
                // with the returned requestCode
                startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);

            }
        });

        if (firebaseCurrentUser != null){
            getData();
        }


    }

    public void getData(){
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Chargement ...");
        progressDialog.show();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").
                child(firebaseCurrentUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                User user = dataSnapshot.getValue(User.class);

                currentUser.setPhoneNumber(user.getPhoneNumber());
                currentUser.setBirthday(user.getBirthday());
                currentUser.setCity(user.getCity());
                currentUser.setFullName(user.getFullName());
                currentUser.setRole(user.getRole());
                currentUser.setUid(firebaseCurrentUser.getUid());

                if(user.getPhotoProfileURL() != null){
                    currentUser.setPhotoProfileURL(user.getPhotoProfileURL());
                        Glide.with(getApplicationContext()).load(currentUser.getPhotoProfileURL()).error(R.drawable.baseline_person_24)
                                .placeholder(R.drawable.baseline_person_24).into(profilePicture);
                }

                phoneNumber.setText(currentUser.getPhoneNumber());
                fullName.setText(currentUser.getFullName());
                city.setText(currentUser.getCity());
                birthday.setText(currentUser.getBirthday());

                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }


        });
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    profilePicture.setImageURI(selectedImageUri);
                }
            }
        }
    }

    private void getImageView() throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
        profilePicture.setImageBitmap(bitmap);
    }

    private void uploadImage(){

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Chargement ...");
        progressDialog.show();

        FirebaseStorage.getInstance().getReference("images/"+ UUID.randomUUID().toString())
                .putFile(selectedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            task.getResult().getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    updateProfilePicture(task.getResult().toString());
                                }
                            });
                            Toast.makeText(ProfileUserActivity.this, "Image a été téléversé.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ProfileUserActivity.this, "Image n'a pas pu être téléversé.", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    private void updateProfilePicture(String url) {
        FirebaseDatabase.getInstance().getReference("users/"
                +FirebaseAuth.getInstance().getCurrentUser().getUid()+
                "/photoProfileURL").setValue(url);

        selectedImageUri = null;
    }


}