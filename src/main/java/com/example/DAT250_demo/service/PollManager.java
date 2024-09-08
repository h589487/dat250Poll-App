package com.example.DAT250_demo.service;

import com.example.DAT250_demo.domain.Poll;
import com.example.DAT250_demo.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private HashMap<String, User> users = new HashMap<>();
    private HashMap<Integer, Poll> polls = new HashMap<>();

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public HashMap<Integer, Poll> getPolls() {
        return polls;
    }

    public void setPolls(HashMap<Integer, Poll> polls) {
        this.polls = polls;
    }
}



