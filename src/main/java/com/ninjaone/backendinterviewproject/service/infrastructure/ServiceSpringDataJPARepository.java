package com.ninjaone.backendinterviewproject.service.infrastructure;

import com.ninjaone.backendinterviewproject.service.domain.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceSpringDataJPARepository extends JpaRepository<Service, UUID> {
}
