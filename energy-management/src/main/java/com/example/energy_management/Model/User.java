package com.example.energy_management.Model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "app_user")
@Data // Folosim @Data în loc de mai multe adnotări individuale
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // Schimbăm numele din `id` în `userId` pentru unicitate

    private String username; // Schimbăm `name` în `username`
    private String userPassword; // Schimbăm `password` în `userPassword`

    @Enumerated(EnumType.STRING) // Asigurăm stocarea ca text a rolului
    private Role userRole; // Schimbăm `role` în `userRole`

    // Metodă personalizată pentru a afișa datele fără parola (dacă este nevoie)
    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
