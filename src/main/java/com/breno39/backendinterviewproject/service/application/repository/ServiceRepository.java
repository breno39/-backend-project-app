package com.breno39.backendinterviewproject.service.application.repository;

import com.breno39.backendinterviewproject.service.domain.Service;

public interface ServiceRepository {
    Service createService(Service service);
}
