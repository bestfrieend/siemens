package com.siemens.challenge.domain.service.impl;

import com.siemens.challenge.application.params.FilterParam;
import com.siemens.challenge.domain.entities.Vehicle;
import com.siemens.challenge.domain.repositories.VehicleRepository;
import com.siemens.challenge.domain.service.VehicleService;
import com.siemens.challenge.platform.exceptions.MissingResourceException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final MongoTemplate template;
    private final VehicleRepository vehicleRepository;

    @Override
    public Page<Vehicle> findAll(FilterParam filter) {
        final Criteria criteria = new Criteria();
        if (StringUtils.hasText(filter.getVin())) {
            criteria.and("vin").is(filter.getVin());
        }
        if (StringUtils.hasText(filter.getName())) {
            criteria.and("name").is(filter.getName());
        }
        if (StringUtils.hasText(filter.getLicensePlateNumber())) {
            criteria.and("licensePlateNumber").is(filter.getLicensePlateNumber());
        }
        final Query query = new Query(criteria).allowSecondaryReads();
        final long total = template.count(query, Vehicle.class);
        PageRequest pageRequest = PageRequest.of(filter.getPageNum(), filter.getPageSize());
        query.with(pageRequest);
        return new PageImpl<>(template.find(query, Vehicle.class), pageRequest, total);
    }

    @Override
    public Vehicle findByVin(String vin) {
        return vehicleRepository.findById(vin).orElseThrow(() -> new MissingResourceException("vin not found"));
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String vin) {
        vehicleRepository.deleteById(vin);
    }

}
