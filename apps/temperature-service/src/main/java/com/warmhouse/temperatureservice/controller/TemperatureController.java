package com.warmhouse.temperatureservice.controller;

import com.warmhouse.temperatureservice.dto.TemperatureDto;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

@Log
@RestController
@RequestMapping("/temperature")
public class TemperatureController {
    private final Random random = new Random();

    @GetMapping
    public TemperatureDto get(@RequestParam("location") String location) {
        log.info("Got request for location %s".formatted(location));
        return TemperatureDto.builder()
                .value(BigDecimal.valueOf(random.nextDouble(-50.0, 150.0)).setScale(2, RoundingMode.DOWN))
                .unit("temperature")
                .timestamp(OffsetDateTime.now())
                .location(location)
                .status("OK")
                .sensorId(UUID.randomUUID().toString())
                .sensorType("sensorType")
                .description("this is random device")
                .build();
    }
}
