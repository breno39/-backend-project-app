package com.ninjaone.backendinterviewproject.device.infrastructure;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceSpringDataJPARepository extends JpaRepository<Device, UUID> {
}
