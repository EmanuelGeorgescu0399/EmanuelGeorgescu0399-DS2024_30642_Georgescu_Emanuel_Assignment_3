package com.example.energy_management.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import com.example.energy_management.Model.Role;
import com.example.energy_management.Repository.UserRepository;
import com.example.energy_management.Model.User;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    // Inițializează aplicația și adaugă utilizatorul implicit doar dacă nu există deja
    @Override
    public void run(String... args) {
        Optional<User> existingUser = userRepository.findByUsername("emanuel");

        if (existingUser.isEmpty()) {
            User defaultUser = User.builder()
                    .username("emanuel")
                    .userRole(Role.ADMIN)
                    .userPassword("1234")
                    .build();

            userRepository.save(defaultUser);
            System.out.println("Utilizator implicit adăugat în baza de date: " + defaultUser.getUsername());
        } else {
            System.out.println("Utilizatorul implicit există deja în baza de date.");
        }
    }
}