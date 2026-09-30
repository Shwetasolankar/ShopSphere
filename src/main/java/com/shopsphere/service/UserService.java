package com.shopsphere.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopsphere.dto.UserResponse;
import com.shopsphere.entity.User;
import com.shopsphere.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    // ==============================
    // CONSTRUCTOR
    // ==============================

    public UserService(
            UserRepository userRepository,
            JwtService jwtService) {

        this.userRepository = userRepository;

        this.jwtService = jwtService;

        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    // ==============================
    // REGISTER USER
    // ==============================

    public UserResponse registerUser(User user) {

        if (user.getName() == null ||
                user.getName().trim().isEmpty()) {

            throw new RuntimeException(
                    "Name is required");
        }

        if (user.getEmail() == null ||
                user.getEmail().trim().isEmpty()) {

            throw new RuntimeException(
                    "Email is required");
        }

        if (user.getPassword() == null ||
                user.getPassword().trim().isEmpty()) {

            throw new RuntimeException(
                    "Password is required");
        }

        if (userRepository.existsByEmail(user.getEmail())) {

            throw new RuntimeException(
                    "Email already registered");
        }

        // ==============================
        // ENCRYPT PASSWORD
        // ==============================

        String encryptedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        User savedUser = userRepository.save(user);

        // ==============================
        // GENERATE JWT TOKEN
        // ==============================

        String token =
                jwtService.generateToken(
                        savedUser.getEmail());

        // ==============================
        // RETURN RESPONSE
        // ==============================

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                token);
    }

    // ==============================
    // LOGIN USER
    // ==============================

    public UserResponse loginUser(
            String email,
            String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Invalid email or password"));

        // ==============================
        // CHECK PASSWORD
        // ==============================

        if (!passwordEncoder.matches(
                password,
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid email or password");
        }

        // ==============================
        // GENERATE JWT TOKEN
        // ==============================

        String token =
                jwtService.generateToken(
                        user.getEmail());

        // ==============================
        // RETURN RESPONSE
        // ==============================

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token);
    }

    // ==============================
    // GET ALL USERS
    // ==============================

    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // ==============================
    // GET USER BY ID
    // ==============================

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: "
                                        + id));
    }

    // ==============================
    // DELETE USER
    // ==============================

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found with id: "
                                        + id));

        userRepository.delete(user);
    }
}