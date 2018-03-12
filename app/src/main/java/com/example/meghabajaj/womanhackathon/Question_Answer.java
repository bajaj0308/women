package com.example.meghabajaj.womanhackathon;

/**
 * Created by MEGHA BAJAJ on 11-03-2018.
 */

public class Question_Answer {
    public String question;
    public String answer;
    public Question_Answer(){
    }
    public Question_Answer(String question,String answer){
        this.question=question;
        this.answer=answer;
    }
    public String getQuestion(){
        return question;
    }
    public void setQuestion(String question){
        this.question=question;
    }
    public String getAnswer(){
        return answer;
    }
    public void setAnswer(String answer){
        this.answer=answer;
    }
}
