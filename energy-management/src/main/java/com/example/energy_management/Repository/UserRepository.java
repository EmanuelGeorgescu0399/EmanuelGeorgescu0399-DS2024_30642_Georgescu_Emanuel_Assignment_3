package com.example.energy_management.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.energy_management.Model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {


    Optional<User> findByUsername(String username);


    Optional<User> findByUsernameAndUserPassword(String username, String userPassword);
}