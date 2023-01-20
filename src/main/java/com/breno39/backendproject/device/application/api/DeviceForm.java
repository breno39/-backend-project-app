package com.breno39.backendproject.device.application.api;

import com.breno39.backendproject.service.domain.domain.Device;
import com.breno39.backendproject.service.domain.domain.DeviceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceForm {
    @NotBlank
    private String systemName;

    @NotNull
    private UUID deviceTypeId;

    public Device toEntity() {
        return Device.builder()
                .systemName(systemName)
                .build();
    }
}
