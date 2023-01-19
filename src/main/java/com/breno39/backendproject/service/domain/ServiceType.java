package com.breno39.backendproject.service.domain;

import com.breno39.backendproject.device.domain.OperatingSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceType {
    WINDOWS_ANTIVIRUS(5L, OperatingSystem.WINDOWS),
    MAC_ANTIVIRUS(7L, OperatingSystem.MAC),
    SCREENSHARE(1L, OperatingSystem.ANY),
    BACKUP(3L, OperatingSystem.ANY),
    PSA(2L, OperatingSystem.ANY);

    private final Long cost;
    private final OperatingSystem system;

}
