package com.example.device.Service;


import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.example.device.Repository.DeviceRepository;
import com.example.device.Model.Device;

import java.util.List;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public List<Device> findAll() {
        return (List<Device>) deviceRepository.findAll();
    }

    public Device saveDevice(Device device) {
        Device entity = new Device();
        entity.setDeviceDescription(device.getDeviceDescription());
        entity.setLocationAddress(device.getLocationAddress());
        entity.setHourlyConsumptionLimit(device.getHourlyConsumptionLimit());
        entity.setOwnerId(device.getOwnerId());
        return deviceRepository.save(entity);
    }

    public Device updateDevice(Device device) {
        return deviceRepository.findById(device.getDeviceId()).map(existingDevice -> {
            existingDevice.setLocationAddress(device.getLocationAddress());
            existingDevice.setDeviceDescription(device.getDeviceDescription());
            existingDevice.setHourlyConsumptionLimit(device.getHourlyConsumptionLimit());
            return deviceRepository.save(existingDevice);
        }).orElse(null);
    }

    public boolean deleteDevice(Long id) {
        deviceRepository.deleteById(id);
        return deviceRepository.findById(id).isEmpty();
    }

    public Device findDeviceById(Long id) {
        return deviceRepository.findById(id).orElseThrow();
    }

    public List<Device> findDevicesByUserId(Long userId) {
        return deviceRepository.findByOwnerId(userId);
    }

    public Device updateDeviceUserId(Device device) {
        return deviceRepository.findById(device.getDeviceId()).map(existingDevice -> {
            existingDevice.setOwnerId(device.getOwnerId());
            return deviceRepository.save(existingDevice);
        }).orElse(null);
    }
}
