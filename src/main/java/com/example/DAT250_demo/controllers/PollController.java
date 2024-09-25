package com.example.DAT250_demo.controllers;

import com.example.DAT250_demo.domain.Poll;
import com.example.DAT250_demo.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/polls")
public class PollController {

    @Autowired
    private PollManager pollManager;
    private int nextId; //Teller for neste ID

    public PollController(PollManager pollManager) {
        this.pollManager = pollManager;
        this.nextId = 1; // Starter med ID 1
    }

    @GetMapping
    public ResponseEntity<HashMap<Integer, Poll>> getAllPolls() {
        HashMap<Integer, Poll> polls = pollManager.getPolls();
        return ResponseEntity.ok(polls);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<Poll> getPoll(@PathVariable Integer pollId) {
        HashMap<Integer, Poll> polls = pollManager.getPolls();
        Poll poll = polls.get(pollId);
        if(poll != null){
            return ResponseEntity.ok(poll);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody Poll poll){
        System.out.println("Received poll data: " + poll);

        HashMap<Integer, Poll> polls = pollManager.getPolls();
        if (poll == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        int id = nextId++;
        polls.put(id, poll);
        return new ResponseEntity<>(poll, HttpStatus.CREATED);
    }




    @PutMapping("/{pollId}")
    public ResponseEntity<String> updatePoll(@PathVariable Integer pollId, @RequestBody Poll poll) {
        HashMap<Integer, Poll> polls = pollManager.getPolls();

        if (polls.containsKey(pollId)) {
            polls.put(pollId, poll);
            return ResponseEntity.ok("Poll updated successfully");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Poll not found");
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<String> deletePoll(@PathVariable Integer pollId) {
        HashMap<Integer, Poll> polls = pollManager.getPolls();
        polls.remove(pollId);
        return ResponseEntity.ok("Poll deleted");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPolls() {
        HashMap<Integer, Poll> polls = pollManager.getPolls();
        polls.clear();
        return ResponseEntity.ok("All polls deleted");
    }
}


