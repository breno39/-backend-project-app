package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.device.domain.Device;
import com.breno39.backendproject.device.domain.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceForm {
    @NotBlank
    private String systemName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DeviceType type;

    public Device toEntity() {
        return Device.builder()
                .systemName(systemName)
                .type(type)
                .build();
    }
}
