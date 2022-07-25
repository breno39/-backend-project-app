package com.ninjaone.backendinterviewproject.service.application.api;

import com.ninjaone.backendinterviewproject.common.annotation.ValueOfEnum;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import com.ninjaone.backendinterviewproject.service.domain.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceForm {

    @NotBlank
    @ValueOfEnum(enumClass = ServiceType.class)
    private ServiceType type;

    public Service toEntity() {
        return new Service(type);
    }
}
