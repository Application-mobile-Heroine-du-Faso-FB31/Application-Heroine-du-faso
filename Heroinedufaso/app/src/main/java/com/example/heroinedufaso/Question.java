package com.example.heroinedufaso;

public class Question {

    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private int correctAns;

    private int exit;

    public Question(String question, String optionA, String optionB, String optionC, int correctAns){
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAns = correctAns;
    }

    public Question(String question, String optionA, String optionB, String optionC, int correctAns, int exit) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.correctAns = correctAns;
        this.exit = exit;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }


    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }
}