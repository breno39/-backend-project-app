package com.breno39.backendproject.service.application.api;

import com.breno39.backendproject.common.annotation.MustBeInServiceType;
import com.breno39.backendproject.service.domain.Service;
import com.breno39.backendproject.service.domain.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceForm {

    @MustBeInServiceType(enumClass = ServiceType.class)
    private ServiceType type;

    public Service toEntity() {
        return new Service(type);
    }
}
