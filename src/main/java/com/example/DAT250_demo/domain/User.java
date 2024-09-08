package com.example.DAT250_demo.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private List<Vote> votes;

    public User() {
        this.votes = new ArrayList<>();
    }

    public User(String username, String email, List<Vote> votes) {
        this.username = username;
        this.email = email;
        this.votes = votes != null ? votes : new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", votes=" + votes +
                '}';
    }
}

