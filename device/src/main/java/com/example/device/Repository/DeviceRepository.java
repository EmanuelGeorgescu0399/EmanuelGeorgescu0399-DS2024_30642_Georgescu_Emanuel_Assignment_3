package com.example.device.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.device.Model.Device;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByOwnerId(Long userId); // Pentru a găsi dispozitivele asociate unui utilizator specific
}
