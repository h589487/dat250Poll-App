package com.example.DAT250_demo.controllers;

import com.example.DAT250_demo.domain.User;
import com.example.DAT250_demo.service.PollManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/users")
public class UserController {

    @Autowired
    private PollManager pollManager; // Ensure PollManager is the correct service for managing users

    @GetMapping
    public ResponseEntity<HashMap<String, User>> getAllUsers() {
        HashMap<String, User> users = pollManager.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username){
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)){
            return ResponseEntity.ok("User found");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(user.getUsername())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }
        users.put(user.getUsername(), user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @PutMapping("/{username}")
    public ResponseEntity<String> updateUser(@PathVariable String username, @RequestBody User user) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            users.put(username, user);
            return ResponseEntity.ok("User updated");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        HashMap<String, User> users = pollManager.getUsers();
        if (users.containsKey(username)) {
            users.remove(username);
            return ResponseEntity.ok("User deleted");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAll() {
        HashMap<String, User> users = pollManager.getUsers();
        users.clear();
        return ResponseEntity.ok("All users deleted");
    }
}

