package com.example.DAT250_demo.service;

import com.example.DAT250_demo.domain.Poll;
import com.example.DAT250_demo.domain.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PollManager {
    private Map<String, User> users = new HashMap<>();
    private Map<String, Poll> polls = new HashMap<>();

    //Metoder til å håndtere brukere
    public User createUser(String username, String email) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        users.put(username, user);
        return user;
    }

    public User getUser(String username){
        return  users.get(username);
    }

    public Poll createPoll(String question, String creatorUsername){
        User creator = getUser(creatorUsername);
        if (creator == null){
            throw new IllegalArgumentException("Creator does not exist");
        }
        Poll poll = new Poll();
        poll.setQuestion(question);

        polls.put(question, poll);
        return poll;
    }

    public Poll getPoll(String question){
        return polls.get(question);
    }
}
