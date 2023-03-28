package com.example.regime;

public class FeedbackMessage {
    public String id;
    public String feedback;
    public String response;
    public String username;
    public FeedbackMessage(String i, String f, String r, String u){
        id = i;
        feedback = f;
        response = r;
        username = u;
    }
}
