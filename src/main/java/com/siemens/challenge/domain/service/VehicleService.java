package com.siemens.challenge.domain.service;

import com.siemens.challenge.application.params.FilterParam;
import com.siemens.challenge.domain.entities.Vehicle;
import org.springframework.data.domain.Page;

public interface VehicleService {

    Page<Vehicle> findAll(FilterParam filter);

    Vehicle findByVin(String vin);

    Vehicle save(Vehicle vehicle);

    void delete(String vin);

}
