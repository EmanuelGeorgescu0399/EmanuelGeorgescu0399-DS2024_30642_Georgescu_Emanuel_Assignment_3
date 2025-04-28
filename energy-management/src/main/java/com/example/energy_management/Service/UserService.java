package com.example.energy_management.Service;

import com.example.energy_management.Dto.DeviceDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.energy_management.Repository.UserRepository;
import com.example.energy_management.Model.User;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    @Value("${device.service.url}")
    private String deviceServiceUrl;

    @Autowired
    public UserService(UserRepository userRepository, RestTemplate restTemplate) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
    }

    // Găsește toți utilizatorii
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    // Salvează un utilizator nou
    public User saveUser(User user) {
        User entity = new User();
        entity.setUserRole(user.getUserRole());
        entity.setUsername(user.getUsername());
        entity.setUserPassword(user.getUserPassword());
        return userRepository.save(entity);
    }

    // Actualizează un utilizator existent
    public User updateUser(User user) {
        return userRepository.findById(user.getUserId()).map(existingUser -> {
            existingUser.setUsername(user.getUsername());
            existingUser.setUserRole(user.getUserRole());
            existingUser.setUserPassword(user.getUserPassword());
            return userRepository.save(existingUser);
        }).orElse(null);
    }

    // Șterge un utilizator după ID
    public boolean deleteUser(Long id) {
        userRepository.deleteById(id);
        return userRepository.findById(id).isEmpty();
    }

    // Găsește un utilizator după ID
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // Găsește un utilizator după nume și parolă
    public User findByNameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndUserPassword(username, password).orElse(null);
    }

    // Găsește dispozitivele asociate unui utilizator după ID-ul acestuia
    public List<DeviceDTO> findDevicesByUserId(Long userId) {
        String url = deviceServiceUrl + "/api/devices/user/" + userId;
        ResponseEntity<List<DeviceDTO>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<DeviceDTO>>() {}
        );
        return response.getBody();
    }
}
