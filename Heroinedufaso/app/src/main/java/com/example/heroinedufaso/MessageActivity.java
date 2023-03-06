package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EditText edtMessageInput;
    private TextView txtChatingWith;
    private ProgressBar progressBar;
    private ImageView imgToolBar, imgSendMessage;
    private ArrayList<Message> messages;

    String usernameOfTheRoomate, phoneNumberRoomate, chatRoomId, userIDOfRommate;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);


        usernameOfTheRoomate = getIntent().getStringExtra("username_of_roomate");
        phoneNumberRoomate  = getIntent().getStringExtra("phone_number_of_roomate");
        userIDOfRommate = getIntent().getStringExtra("user_id");

        recyclerView = findViewById(R.id.recyclerMessages);
        edtMessageInput = findViewById(R.id.edtText);
        txtChatingWith = findViewById(R.id.txtChatingWith);
        progressBar = findViewById(R.id.progressMessages);
        imgToolBar = findViewById(R.id.img_toolbars);
        imgSendMessage = findViewById(R.id.imgSendMessage);


        txtChatingWith.setText(usernameOfTheRoomate);

        messages = new ArrayList<>();
    }

    public void setupRoom(){
        String myUsernameID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(userIDOfRommate.compareTo(myUsernameID)>0){
            chatRoomId = myUsernameID + usernameOfTheRoomate;
        } else if(userIDOfRommate.compareTo(myUsernameID) == 0){
            chatRoomId = myUsernameID + usernameOfTheRoomate;
        }else{
            chatRoomId = userIDOfRommate + myUsernameID;
        }
    }
}