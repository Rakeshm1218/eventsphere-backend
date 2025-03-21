package com.project.eventsphereBackend.controllers;

import com.project.eventsphereBackend.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.project.eventsphereBackend.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create a user (admin or normal based on isAdmin value)
    @PostMapping("/create")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user.getUsername(), user.getPassword(), user.getIsAdmin());
    }

    // Get all users
    @GetMapping("/all")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Update user
    @PutMapping("/update/{id}")
    public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel updatedUser) {
        return userService.updateUser(id, updatedUser);
    }
}
