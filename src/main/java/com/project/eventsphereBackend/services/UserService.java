package com.project.eventsphereBackend.services;

import com.project.eventsphereBackend.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.eventsphereBackend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // Create user (admin or normal based on isAdmin value)
    public UserModel createUser(String username, String email, String password, Boolean isAdmin) {
        return userRepository.save(new UserModel(null, username, email, password, isAdmin));
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

    //delete user
    public boolean deleteUser(Long userId) {
        Optional<UserModel> user = userRepository.findById(userId);
        if (user.isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }


    public UserModel FindByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));
    }
}
