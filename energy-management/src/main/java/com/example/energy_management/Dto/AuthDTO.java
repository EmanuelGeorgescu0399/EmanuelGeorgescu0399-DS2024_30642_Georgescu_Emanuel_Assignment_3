package com.example.energy_management.Dto;

import com.example.energy_management.Model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthDTO {

    private String username;
    private String userPassword;
    private Role userRole;
}