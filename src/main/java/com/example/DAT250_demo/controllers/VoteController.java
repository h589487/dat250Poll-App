package com.example.DAT250_demo.controllers;

import com.example.DAT250_demo.domain.User;
import com.example.DAT250_demo.domain.Vote;
import com.example.DAT250_demo.service.PollManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/users/{username}/votes")
public class VoteController {

    private final PollManager pollManager;

    public VoteController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping
    public ResponseEntity<List<Vote>> getUserVotes(@PathVariable String username) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();
            return ResponseEntity.ok(votes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{voteId}")
    public ResponseEntity<Vote> getUserVoteById(@PathVariable String username, @PathVariable Integer voteId) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();
            if (voteId >= 0 && voteId < votes.size()) {
                Vote vote = votes.get(voteId);
                return ResponseEntity.ok(vote);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping
    public ResponseEntity<Vote> createUserVote(@PathVariable String username, @RequestBody Vote vote) {
        HashMap<String, User> users = pollManager.getUsers();
        //if (users.containsKey(username)) {
          //  User user = users.get(username);

        User user = users.getOrDefault(username, new User(username, null, new ArrayList<>()));

        List<Vote> votes = user.getVotes();
            votes.add(vote);
            user.setVotes(votes);

        // Lagre endringen hvis det er nødvendig
        users.put(user.getUsername(), user);

            return ResponseEntity.status(HttpStatus.CREATED).body(vote);
        //}
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/{voteId}")
    public ResponseEntity<Vote> updateUserVote(@PathVariable String username, @PathVariable Integer voteId, @RequestBody Vote newVote) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();
            if (voteId >= 0 && voteId < votes.size()) {
                votes.set(voteId, newVote);
                user.setVotes(votes);
                return ResponseEntity.ok(newVote);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserVotes(@PathVariable String username) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();
            votes.clear();
            user.setVotes(votes);
            return ResponseEntity.ok("All votes deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping("/{voteId}")
    public ResponseEntity<List<Vote>> deleteUserVoteById(@PathVariable String username, @PathVariable Integer voteId) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();
            if (voteId >= 0 && voteId < votes.size()) {
                votes.remove((int) voteId);
                user.setVotes(votes);
                return ResponseEntity.ok(votes);
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}


