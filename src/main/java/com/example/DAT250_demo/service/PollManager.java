package com.example.DAT250_demo.service;

import com.example.DAT250_demo.domain.Poll;
import com.example.DAT250_demo.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    //test metode foreløpig
    public PollManager() {
        User mockUser = new User();
        mockUser.setUsername("defaultUser");
        mockUser.setVotes(new ArrayList<>());  // Initialiser stemmelisten som tom
        users.put("defaultUser", mockUser);

    }


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



