package com.breno39.backendproject.service.infrastructure;

import com.breno39.backendproject.service.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceSpringDataJPARepository extends JpaRepository<Service, UUID> {
}
