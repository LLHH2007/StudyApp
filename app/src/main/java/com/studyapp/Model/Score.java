package com.studyapp.Model;

public class Score {
    private int id;
    private String name;
    private double score;
    private String date;
    private String subject;

    public Score(String name,String subject, double score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
        this.subject=subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
