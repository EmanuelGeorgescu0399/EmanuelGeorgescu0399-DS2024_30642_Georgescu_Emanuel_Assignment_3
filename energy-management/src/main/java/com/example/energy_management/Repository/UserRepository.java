package com.example.energy_management.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.energy_management.Model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // Căutare utilizator după nume
    Optional<User> findByUsername(String username); // Schimbăm `findByName` în `findByUsername`

    // Căutare utilizator după nume și parolă
    Optional<User> findByUsernameAndUserPassword(String username, String userPassword); // Schimbăm numele metodei și parametrilor
}