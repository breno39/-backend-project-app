package com.ninjaone.backendinterviewproject.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceType {
    WINDOWS_ANTIVIRUS(5L), MAC_ANTIVIRUS(7L), SCREENSHARE(1L), BACKUP(3L), PSA(2L);

    private final Long cost;

}
