package com.kamad.user.service.controller;

import com.kamad.user.service.entities.User;
import com.kamad.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

// Create user with this method
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1 =userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }
// Single user get by id
    @GetMapping("/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user=userService.getUser(userId);
        return ResponseEntity.ok(user);
    }
// Get all user in the list
    @GetMapping
    public ResponseEntity<List<User>> getAllUser(){
        List<User> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }
}
