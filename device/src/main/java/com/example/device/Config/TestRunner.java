package com.example.device.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.device.Repository.DeviceRepository;
import com.example.device.Model.Device;

@Component
public class TestRunner implements CommandLineRunner {

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public void run(String... args) {
        Device newDevice = Device.builder()
                .deviceDescription("Smart Device Test Bia")
                .locationAddress("Strada Castanilor")
                .hourlyConsumptionLimit(3.4)
                .ownerId(1)
                .build();

        deviceRepository.save(newDevice);
        System.out.println("Device initialized and saved: " + deviceRepository.findAll());
    }
}
