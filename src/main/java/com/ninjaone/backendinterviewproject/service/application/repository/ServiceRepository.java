package com.ninjaone.backendinterviewproject.service.application.repository;

import com.ninjaone.backendinterviewproject.service.domain.Service;

public interface ServiceRepository {
    Service createService(Service service);
}
