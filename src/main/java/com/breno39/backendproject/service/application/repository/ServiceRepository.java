package com.breno39.backendproject.service.application.repository;

import com.breno39.backendproject.service.domain.Service;

public interface ServiceRepository {
    Service createService(Service service);
}
