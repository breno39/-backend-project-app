package com.ninjaone.backendinterviewproject.service.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ServiceType {
    WINDOWS_ANTIVIRUS(5), MAC_ANTIVIRUS(7), SCREENSHARE(1), BACKUP(3), PSA(2);

    private final Integer cost;

}
