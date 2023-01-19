package com.breno39.backendinterviewproject.device.application.api;

import com.breno39.backendinterviewproject.device.domain.Device;
import com.breno39.backendinterviewproject.device.domain.DeviceType;
import lombok.Getter;

@Getter
public class DeviceDTO {
    private final String systemName;

    private final DeviceType type;

    public DeviceDTO(Device device) {
        this.systemName = device.getSystemName();
        this.type = device.getType();
    }

}
