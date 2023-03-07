package com.example.heroinedufaso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private MessageAdapter messageAdapter;

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

        messages = new ArrayList<>();

        messageAdapter = new MessageAdapter(messages, getIntent().getStringExtra("my_img"),
                getIntent().getStringExtra("img_of_roomate"), MessageActivity.this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(messageAdapter);


        txtChatingWith.setText(usernameOfTheRoomate);

        Glide.with(MessageActivity.this).load(getIntent().getStringExtra("img_of_roomate")).error(R.drawable.baseline_person_pink_24)
                .placeholder(R.drawable.baseline_person_pink_24).into(imgToolBar);

        imgSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase.getInstance().getReference("messages/"+chatRoomId).push().setValue(


                        new Message(edtMessageInput.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getUid(),
                                getIntent().getStringExtra("id_receive")));

                edtMessageInput.setText("");
            }
        });


        setupRoom();
    }

    private void setupRoom(){
        String myUsernameID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        if(userIDOfRommate.compareTo(myUsernameID)>0){
            chatRoomId = myUsernameID + userIDOfRommate;
        } else if(userIDOfRommate.compareTo(myUsernameID) == 0){
            chatRoomId = myUsernameID + userIDOfRommate;
        }else{
            chatRoomId = userIDOfRommate + myUsernameID;
        }
        attachMessages(chatRoomId);
    }

    private void attachMessages(String chatRoomId){
        FirebaseDatabase.getInstance().getReference("messages")
                .child(chatRoomId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    messages.add(dataSnapshot.getValue(Message.class));
                }
                messageAdapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(messages.size()-1);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}