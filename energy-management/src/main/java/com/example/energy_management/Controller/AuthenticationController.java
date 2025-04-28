package com.example.energy_management.Controller;

import com.example.energy_management.Model.User;
import com.example.energy_management.Service.UserService;
import com.example.energy_management.Dto.AuthDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://localhost", allowCredentials = "true")
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService authService;

    public AuthenticationController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO credentials) {
        User authenticatedUser = authService.findByNameAndPassword(credentials.getUsername(), credentials.getUserPassword());
        if (authenticatedUser != null) {
            return ResponseEntity.ok(authenticatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
