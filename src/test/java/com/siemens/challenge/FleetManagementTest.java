package com.siemens.challenge;

import com.siemens.challenge.domain.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Created by
 *
 * @author Wasif
 */
@SpringBootTest
public class FleetManagementTest {
    @Autowired
    VehicleService vehicleService;

    @Test
    public void contextLoads() {
    }
}
