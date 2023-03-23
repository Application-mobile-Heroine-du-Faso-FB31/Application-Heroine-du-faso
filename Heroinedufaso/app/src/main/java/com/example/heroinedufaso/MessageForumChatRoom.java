package com.example.heroinedufaso;

public class MessageForumChatRoom {

    private String roomID;
    private String sender;

    public MessageForumChatRoom(){

    }

    public MessageForumChatRoom(String roomID, String sender) {
        this.roomID = roomID;
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public String getRoomID() {
        return roomID;
    }
}
