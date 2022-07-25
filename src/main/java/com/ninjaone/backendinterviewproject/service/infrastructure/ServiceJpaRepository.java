package com.ninjaone.backendinterviewproject.service.infrastructure;

import com.ninjaone.backendinterviewproject.service.application.repository.ServiceRepository;
import com.ninjaone.backendinterviewproject.service.application.service.ServiceSpringDataJPAService;
import com.ninjaone.backendinterviewproject.service.domain.Service;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceJpaRepository implements ServiceRepository {
    private Logger logger = LoggerFactory.getLogger(ServiceJpaRepository.class);

    private final ServiceSpringDataJPARepository repository;

    @Override
    public Service createService(Service service) {
        logger.info("[START] - ServiceJpaRepository - createService");
        Service createdService = repository.saveAndFlush(service);
        logger.info("[FINISH] - ServiceJpaRepository - createService");
        return createdService;
    }
}
