package com.siemens.challenge.domain.service.impl;

import com.mongodb.BasicDBObject;
import com.siemens.challenge.application.params.FilterParam;
import com.siemens.challenge.domain.entities.Vehicle;
import com.siemens.challenge.domain.repositories.VehicleRepository;
import com.siemens.challenge.platform.exceptions.MissingResourceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    VehicleRepository vehicleRepository;
    @Mock
    MongoTemplate template;
    @InjectMocks
    VehicleServiceImpl vehicleService;

    private Vehicle generateVehicle() {
        return new Vehicle("12345678901234567", "ABC-1234-02", "Mazda1", new BasicDBObject().append("color", "red"));
    }


    @Test
    void fetchAllTest() {

        //given
        given(template.count(any(Query.class), eq(Vehicle.class))).willReturn(1L);
        given(template.find(any(Query.class), eq(Vehicle.class))).willReturn(List.of(generateVehicle()));
        //when
        FilterParam param = new FilterParam();
        param.setVin(generateVehicle().getVin());
        param.setName("name");
        param.setLicensePlateNumber("licensePlate");
        param.setPageSize(5);
        param.setPageNum(0);
        Page<Vehicle> page = vehicleService.findAll(param);

        //then
        assertNotNull(page);
        assertEquals(1, page.getTotalElements());

    }

    @Test
    void findByVinTest() {
        //given
        given(vehicleRepository.findById(eq(generateVehicle().getVin()))).willReturn(Optional.of(generateVehicle()));
        //when
        Vehicle vehicle = vehicleService.findByVin(generateVehicle().getVin());
        //then
        assertNotNull(vehicle);
    }

    @Test
    void findByVinNegativeTest() {
        //given
        given(vehicleRepository.findById(anyString())).willReturn(Optional.empty());
        //when
        Assertions.assertThrows(MissingResourceException.class, () -> vehicleService.findByVin("vin"));
    }

    @Test
    void deleteTest() {
        //when
        vehicleService.delete(generateVehicle().getVin());
        //then
        then(vehicleRepository).should().deleteById(eq(generateVehicle().getVin()));
    }

    @Test
    void saveTest() {
        //given
        given(vehicleRepository.save(any(Vehicle.class))).willReturn(generateVehicle());
        //when
        Vehicle vehicle = vehicleService.save(generateVehicle());
        //then
        assertEquals(generateVehicle().getVin(), vehicle.getVin());
    }

    @Test
    void updateTest() {
        //given
        given(vehicleRepository.save(any(Vehicle.class))).willReturn(generateVehicle());
        //when
        Vehicle vehicle = vehicleService.save(generateVehicle());
        //then
        assertEquals(generateVehicle().getVin(), vehicle.getVin());
    }


}