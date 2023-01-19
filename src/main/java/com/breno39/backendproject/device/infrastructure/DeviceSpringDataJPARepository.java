package com.breno39.backendproject.device.infrastructure;

import com.breno39.backendproject.device.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceSpringDataJPARepository extends JpaRepository<Device, UUID> {
    @Query(name = "Device.getTotalMonthlyCostById")
    Optional<Long> getTotalMonthlyCostById(@Param("deviceId") UUID deviceId);
}
