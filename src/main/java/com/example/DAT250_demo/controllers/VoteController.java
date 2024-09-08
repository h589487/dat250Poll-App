package com.example.DAT250_demo.controllers;

import com.example.DAT250_demo.domain.Poll;
import com.example.DAT250_demo.domain.User;
import com.example.DAT250_demo.domain.Vote;
import com.example.DAT250_demo.domain.VoteOption;
import com.example.DAT250_demo.service.PollManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users/{username}/votes")
public class VoteController {

    private final PollManager pollManager;

    public VoteController(PollManager pollManager) {
        this.pollManager = pollManager;
    }

    @GetMapping("/users/{username}/votes")
    public ResponseEntity<List<Vote>> getUserVotes(@PathVariable String username) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes(); // Hent stemmer for brukeren
            return ResponseEntity.ok(votes); // Returner listen over stemmer med HTTP status 200 OK
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/users/{username}/votes/{voteId}")
    public ResponseEntity<Vote> getUserVoteById(@PathVariable String username, @PathVariable Integer voteId) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();

            // Sjekk om stemmen med gitt ID eksisterer i brukerens stemmeliste
            if (voteId >= 0 && voteId < votes.size()) {
                Vote vote = votes.get(voteId);
                return ResponseEntity.ok(vote); // Returner den spesifikke stemmen med HTTP status 200 OK
            }

            // Hvis stemmen med ID ikke finnes, returner 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }


    @PostMapping("/users/{username}/votes")
    public ResponseEntity<Vote> createUserVote(@PathVariable String username, @RequestBody Vote vote) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();

            // Legg til den nye stemmen i brukerens liste
            votes.add(vote);

            // Oppdater brukerens stemmeliste
            user.setVotes(votes);

            // Returner den nyopprettede stemmen med HTTP status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(vote);
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PutMapping("/users/{username}/votes/{voteId}")
    public ResponseEntity<Vote> updateUserVote(@PathVariable String username, @PathVariable Integer voteId, @RequestBody Vote newVote) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();

            // Sjekk om stemmen med gitt ID eksisterer i brukerens stemmeliste
            if (voteId >= 0 && voteId < votes.size()) {
                votes.set(voteId, newVote); // Oppdater stemmen med ny stemme
                user.setVotes(votes); // Oppdater brukerens stemmeliste

                return ResponseEntity.ok(newVote); // Returner den oppdaterte stemmen med HTTP status 200 OK
            }

            // Hvis stemmen med ID ikke finnes, returner 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @DeleteMapping("/users/{username}/votes")
    public ResponseEntity<String> deleteUserVotes(@PathVariable String username) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();

            // Tøm listen over stemmer
            votes.clear();
            user.setVotes(votes); // Oppdater brukerens stemmeliste

            return ResponseEntity.ok("All votes deleted"); // Returner en melding med HTTP status 200 OK
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping("/users/{username}/votes/{voteId}")
    public ResponseEntity<List<Vote>> deleteUserVoteById(@PathVariable String username, @PathVariable Integer voteId) {
        HashMap<String, User> users = pollManager.getUsers();

        // Sjekk om brukeren eksisterer
        if (users.containsKey(username)) {
            User user = users.get(username);
            List<Vote> votes = user.getVotes();

            // Sjekk om stemmen med den spesifikke ID eksisterer i brukerens stemmeliste
            if (voteId >= 0 && voteId < votes.size()) {
                votes.remove((int) voteId); // Fjern stemmen med den spesifikke ID
                user.setVotes(votes); // Oppdater brukerens stemmeliste

                return ResponseEntity.ok(votes); // Returner den oppdaterte listen med HTTP status 200 OK
            }

            // Hvis stemmen med ID ikke finnes, returner 404 Not Found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        // Hvis brukeren ikke finnes, returner 404 Not Found
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }



}

