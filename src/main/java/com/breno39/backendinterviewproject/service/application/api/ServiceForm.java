package com.breno39.backendinterviewproject.service.application.api;

import com.breno39.backendinterviewproject.common.annotation.MustBeInServiceType;
import com.breno39.backendinterviewproject.service.domain.Service;
import com.breno39.backendinterviewproject.service.domain.ServiceType;
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
