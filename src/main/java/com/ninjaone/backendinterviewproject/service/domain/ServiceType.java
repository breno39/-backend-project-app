package com.ninjaone.backendinterviewproject.service.domain;

public enum ServiceType {
    WINDOWS_ANTIVIRUS(5), MAC_ANTIVIRUS(7), SCREENSHARE(1), BACKUP(3), PSA(2);

    private final Integer cost;

    ServiceType(Integer cost) {
        this.cost = cost;
    }
}
