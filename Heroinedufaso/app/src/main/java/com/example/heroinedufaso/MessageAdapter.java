package com.example.heroinedufaso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Message> messages;
    private String senderImg, receiverImg;
    private Context context;

    public MessageAdapter(ArrayList<Message> messages, String senderImg, String receiverImg, Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.messageholder, parent, false);


        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.txtMessage.setText(messages.get(position).getContent());

        ConstraintLayout constraintLayout = holder.ccll;

        if(messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())){

//            Glide.with(context).load(senderImg).error(R.drawable.baseline_person_pink_24)
//                    .placeholder(R.drawable.baseline_person_pink_24).into(holder.profileImg);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
//            constraintSet.clear(R.id.profile_cardView, ConstraintSet.LEFT);
            constraintSet.clear(R.id.txt_message_content, ConstraintSet.RIGHT);
//            constraintSet.connect(R.id.profile_cardView, ConstraintSet.RIGHT, R.id.cclayout,ConstraintSet.LEFT, 0);
            constraintSet.connect(R.id.txt_message_content, ConstraintSet.LEFT, R.id.cclayout,ConstraintSet.LEFT, 0);
            constraintSet.applyTo(constraintLayout);
        }else{
//            Glide.with(context).load(receiverImg).error(R.drawable.baseline_person_pink_24)
//                    .placeholder(R.drawable.baseline_person_pink_24).into(holder.profileImg);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
//            constraintSet.clear(R.id.profile_cardView, ConstraintSet.RIGHT);
            constraintSet.clear(R.id.txt_message_content, ConstraintSet.LEFT);
//            constraintSet.connect(R.id.profile_cardView, ConstraintSet.LEFT, R.id.cclayout,ConstraintSet.RIGHT, 0);
            constraintSet.connect(R.id.txt_message_content, ConstraintSet.RIGHT, R.id.cclayout,ConstraintSet.RIGHT, 0);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        ConstraintLayout ccll;
        TextView txtMessage;
//        ImageView profileImg;

        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            ccll = itemView.findViewById(R.id.cclayout);
            txtMessage = itemView.findViewById(R.id.txt_message_content);
//            profileImg = itemView.findViewById(R.id.small_profile_img);
        }
    }
}
