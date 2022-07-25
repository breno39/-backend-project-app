package com.ninjaone.backendinterviewproject.device.application.api;

import com.ninjaone.backendinterviewproject.device.domain.Device;
import com.ninjaone.backendinterviewproject.device.domain.DeviceType;
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
