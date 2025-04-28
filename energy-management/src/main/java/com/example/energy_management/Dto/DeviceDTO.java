package com.example.energy_management.Dto;

import lombok.Data;

@Data
public class DeviceDTO {
    private Long deviceId;
    private String deviceDescription;
    private String deviceAddress;
    private double maxConsumptionPerHour;
    private int ownerId;
}