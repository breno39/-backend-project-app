package com.breno39.backendinterviewproject.service.infrastructure;

import com.breno39.backendinterviewproject.service.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceSpringDataJPARepository extends JpaRepository<Service, UUID> {
}
