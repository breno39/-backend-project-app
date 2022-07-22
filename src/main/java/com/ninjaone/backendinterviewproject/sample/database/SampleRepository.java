package com.ninjaone.backendinterviewproject.sample.database;

import com.ninjaone.backendinterviewproject.sample.model.Sample;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends CrudRepository<Sample, String> {}
