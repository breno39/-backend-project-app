package com.breno39.backendproject.service.application.api;

import com.breno39.backendproject.service.domain.Service;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ServiceForm {

    private UUID ServiceTypeId;

    public Service toEntity() {
        return Service.builder().build();
    }
}
