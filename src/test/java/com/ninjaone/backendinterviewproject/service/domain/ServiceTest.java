package com.ninjaone.backendinterviewproject.service.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    @Test
    void mustReturnTrueWhenGivenTypeIsEqualToDomainServiceType() {
        var type = ServiceType.BACKUP;
        var service = new Service(type);

        var returnedIsType = service.isType(type);

        Assertions.assertTrue(returnedIsType);
    }

    @Test
    void mustReturnFalseWhenGivenTypeIsNotEqualToDomainServiceType() {
        var type = ServiceType.WINDOWS_ANTIVIRUS;
        var service = new Service(type);

        var returnedIsType = service.isType(ServiceType.BACKUP);

        Assertions.assertFalse(returnedIsType);
    }
}