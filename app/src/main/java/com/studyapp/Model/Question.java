package com.studyapp.Model;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String question;
    private String ans_a;
    private String ans_b;
    private String ans_c;
    private String ans_d;
    private String result;
    private String subject;
    private String answer="";
    public int choiceID = -1; //ho tro check ID radioGroup

    public Question(){

    }

    public Question(int id, String question, String ans_a, String ans_b, String ans_c, String ans_d, String result,  String subject, String answer){
        this.id = id;
        this.question = question;
        this.ans_a = ans_a;
        this.ans_b=ans_b;
        this.ans_c=ans_c;
        this.ans_d=ans_d;
        this.result=result;
        this.subject=subject;
        this.answer=answer;
    }

    public int getId() {
        return id;
    }

    public String getAns_a() {
        return ans_a;
    }

    public String getAns_b() {
        return ans_b;
    }

    public String getAns_c() {
        return ans_c;
    }

    public String getAns_d() {
        return ans_d;
    }

    public String getQuestion() {
        return question;
    }

    public String getResult() {
        return result;
    }

    public String getSubject() {
        return subject;
    }

    public void setAns_a(String ans_a) {
        this.ans_a = ans_a;
    }

    public void setAns_b(String ans_b) {
        this.ans_b = ans_b;
    }

    public void setAns_c(String ans_c) {
        this.ans_c = ans_c;
    }

    public void setAns_d(String ans_d) {
        this.ans_d = ans_d;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return question;
    }
}
