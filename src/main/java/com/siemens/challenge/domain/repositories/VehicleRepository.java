package com.siemens.challenge.domain.repositories;

import com.siemens.challenge.domain.entities.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VehicleRepository extends MongoRepository<Vehicle, String> {
}
