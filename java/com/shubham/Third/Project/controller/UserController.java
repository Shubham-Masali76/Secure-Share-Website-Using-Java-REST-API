package com.shubham.Third.Project.controller;

import com.shubham.Third.Project.entity.UserEntity;
import com.shubham.Third.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserEntity user) {
        userService.register(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody UserEntity loginUser) {
        Optional<UserEntity> userOpt = userService.login(loginUser.getEmail(), loginUser.getPassword());

        if (userOpt.isEmpty()) {
            return "Invalid credentials";
        }

        return "Login successful, ID: " + userOpt.get().getId();
    }

    @GetMapping("/id")
    public ResponseEntity<Long> getUserIdByEmail(@RequestParam String email) {
        return userService.getByEmail(email)
        .map(user -> ResponseEntity.ok(user.getId()))
        .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String newPassword = payload.get("newPassword");

        Optional<UserEntity> userOpt = userService.getByEmail(email);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        UserEntity user = userOpt.get();
        user.setPassword(newPassword);
        userService.register(user); 
        return ResponseEntity.ok("Password reset successfully");
    }

    @PutMapping("/update-username/{id}")
    public ResponseEntity<String> updateUsername(@PathVariable Long id, @RequestBody Map<String, String> body) {
        return userService.getById(id).map(user -> {
        user.setUsername(body.get("username"));
        userService.register(user);  
        return ResponseEntity.ok("Username updated");
        })
        .orElse(ResponseEntity.notFound().build());   
    }   

}
