package com.siemens.challenge.application.controller;

import com.siemens.challenge.application.enums.ResultCodeEnum;
import com.siemens.challenge.application.params.FilterParam;
import com.siemens.challenge.application.params.VehicleParam;
import com.siemens.challenge.application.response.CommonResponse;
import com.siemens.challenge.application.response.ResultBuilderUtil;
import com.siemens.challenge.domain.service.impl.VehicleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping("/vehicles")
@Slf4j
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object getVehicles(@Valid FilterParam filter) {
        return vehicleService.findAll(filter);
    }

    @GetMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse findVehicle(@PathVariable String vin) {
        return ResultBuilderUtil.buildSuccess(vehicleService.findByVin(vin));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse create(@RequestBody VehicleParam param) {
        return ResultBuilderUtil.buildSuccess(vehicleService.save(param.toVehicle()));
    }

    @PutMapping("/{vin}")
    @ResponseStatus(HttpStatus.OK)
    public CommonResponse update(@PathVariable String vin, @RequestBody VehicleParam param) {
        return ResultBuilderUtil.buildSuccess(vehicleService.save(param.toVehicle()));
    }

    @DeleteMapping("/{vin}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CommonResponse delete(@PathVariable String vin) {
        vehicleService.delete(vin);
        return ResultBuilderUtil.buildSuccess(ResultCodeEnum.SUCCESS.getMessage());
    }
}
