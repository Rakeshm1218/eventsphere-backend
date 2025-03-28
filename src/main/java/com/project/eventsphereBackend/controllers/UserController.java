package com.project.eventsphereBackend.controllers;

import com.project.eventsphereBackend.models.UserModel;
import com.project.eventsphereBackend.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.eventsphereBackend.services.UserService;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Create a user (admin or normal based on isAdmin value)
    @PostMapping("/users/create")
    public UserModel createUser(@RequestBody UserModel user) {
        return userService.createUser(user.getUsername(),user.getEmail(), user.getPassword(), user.getIsAdmin());
    }

    // Get all users
    @GetMapping("admin/users/getAll")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get user by ID
    @GetMapping("admin/users/{id}")
    public UserModel getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    // Update user
    @PutMapping("admin/users/update/{userId}")
    public ResponseEntity<UserModel> updateUser(@PathVariable Long userId, @RequestBody UserModel updatedUser) {
        UserModel user = userService.updateUser(userId, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("admin/users/delete/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        boolean isDeleted = userService.deleteUser(userId); // Assume the service returns a boolean

        if (isDeleted) {
            return ResponseEntity.ok("Deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while deleting user");
        }
    }



    //login user

    @PostMapping("/session/login")
    public ResponseEntity<Object> LoginUsingSession(
            @RequestBody UserModel user,
            HttpServletRequest request)
    {
        UserModel dbUser = userService.FindByEmail(user.email);
        if(dbUser != null)
        {
            if(user.password.equals(dbUser.password))
            {
                // authentication successful.
                var session = request.getSession();
                session.setAttribute("email", dbUser.email);
                session.setAttribute("isAdmin", dbUser.getIsAdmin());

                Map<String, Object> response = new HashMap<>();
                response.put("message", "Login Successful");
                response.put("username", dbUser.getUsername());
                response.put("email", dbUser.getEmail());
                response.put("isAdmin", dbUser.getIsAdmin());

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        // Failure response
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid credentials"));
    }

    @PostMapping("/session/logout")
    public ResponseEntity<String> LogoutUsingSession(HttpServletRequest request) {
        var session = request.getSession(false); // Get session, don't create a new one if it doesn't exist

        if (session != null) {
            session.invalidate(); // Invalidate the session
            return ResponseEntity.status(HttpStatus.OK).body("Logout Successful");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No active session found");
    }


    @GetMapping("admin/users/getAllusers")
    public ResponseEntity<Object> ListUsers(HttpServletRequest request)
    {
        // Get the session and check if username data is set.
        var session = request.getSession();
        var usernameData = session.getAttribute("mail");
        var isAdminObj = session.getAttribute("isAdmin");
        if(usernameData == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORISED");
        }
        boolean isAdmin = Boolean.parseBoolean(isAdminObj.toString());

        // auth successful, now get users and return.
        if(isAdmin){
            var users = userService.getAllUsers();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORISED");

    }

}
