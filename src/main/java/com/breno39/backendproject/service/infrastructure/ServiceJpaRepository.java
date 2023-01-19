package com.breno39.backendproject.service.infrastructure;

import com.breno39.backendproject.service.application.repository.ServiceRepository;
import com.breno39.backendproject.service.domain.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceJpaRepository implements ServiceRepository {
    private final Logger logger = LoggerFactory.getLogger(ServiceJpaRepository.class);

    private final ServiceSpringDataJPARepository repository;

    @Override
    public Service createService(Service service) {
        logger.info("[START] - ServiceJpaRepository - createService");
        Service createdService = repository.save(service);
        logger.info("[FINISH] - ServiceJpaRepository - createService");
        return createdService;
    }
}
