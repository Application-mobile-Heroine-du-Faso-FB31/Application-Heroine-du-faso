package com.example.heroinedufaso;

public class Post {
    private String contains;
    private String owner;
    private String username;

    public Post(){}

    public Post(String contains,String username){
        this.contains = contains;
        this.username = username;
    }

    public Post(String contains,String owner, String username){
        this.contains = contains;
        this.owner = owner;
        this.username = username;

    }


    public String getContains() {
        return contains;
    }

    public void setContains(String contains) {
        this.contains = contains;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
