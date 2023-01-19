package com.breno39.backendproject.device.domain;

import lombok.Getter;

@Getter
public enum DeviceType {
    WINDOWS_WORKSTATION(OperatingSystem.WINDOWS), WINDOWS_SERVER(OperatingSystem.WINDOWS), MAC(OperatingSystem.MAC);

    private OperatingSystem system;

    DeviceType(OperatingSystem system) {
        this.system = system;
    }
}
