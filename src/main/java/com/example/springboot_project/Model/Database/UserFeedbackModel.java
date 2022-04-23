package com.example.springboot_project.Model.Database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "feedback")
public class UserFeedbackModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int feedbackId;
    private String userName;
    private String email;
    private String feedback;

    public UserFeedbackModel() {
    }

    public UserFeedbackModel(int feedbackId, String userName, String email, String feedback) {
        this.feedbackId = feedbackId;
        this.userName = userName;
        this.email = email;
        this.feedback = feedback;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "UserFeedback{" +
                "feedbackId=" + feedbackId +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
