package com.project.eventsphereBackend.services;

import com.project.eventsphereBackend.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.eventsphereBackend.repository.UserRepository;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user (admin or normal based on isAdmin value)
    public UserModel createUser(String username, String password, Boolean isAdmin) {
        return userRepository.save(new UserModel(null, username, password, isAdmin));
    }

    // Get all users
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    // Find user by ID
    public UserModel findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    // Update user
    public UserModel updateUser(Long id, UserModel updatedUser) {
        UserModel existingUser = findById(id);
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setIsAdmin(updatedUser.getIsAdmin());
        return userRepository.save(existingUser);
    }
}
