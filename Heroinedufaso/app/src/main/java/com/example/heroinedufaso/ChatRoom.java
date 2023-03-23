package com.example.heroinedufaso;

import java.util.ArrayList;

public class ChatRoom {
    private String id;
    private String name;
    private ArrayList<User> members;
    private ArrayList <MessageForumChatRoom> messages;

    public ChatRoom(){
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public ChatRoom (String id, String name){
        this.id = id;
        this.name = name;
        members = new ArrayList<>();
        messages = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(User user){
        members.add(user);
    }
    public void removeMember(User user){
        members.remove(user);
    }

    public ArrayList<MessageForumChatRoom> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageForumChatRoom> messages) {
        this.messages = messages;
    }

    public ArrayList<User> getMembers(){
        return members;
    }
    public void setMembers(ArrayList<User> members){
        this.members = members;
    }

    public int countMembers(){
        return members.size();
    }
}
