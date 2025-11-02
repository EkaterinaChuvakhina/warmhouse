package com.warmhouse.telemetryservice.controller;

import com.warmhouse.telemetryservice.entity.TelemetryData;
import com.warmhouse.telemetryservice.service.TelemetryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/telemetry")
public class TelemetryController {

    private final TelemetryService telemetryService;

    public TelemetryController(TelemetryService telemetryService) {
        this.telemetryService = telemetryService;
    }

    @PostMapping
    public ResponseEntity<String> saveTelemetry(@RequestBody TelemetryData data) {
        try {
            if (data.getTimestamp() == null) {
                data.setTimestamp(Instant.now());
            }
            telemetryService.saveTelemetry(data);
            return ResponseEntity.ok("Telemetry data saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save telemetry: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<TelemetryData>> getTelemetry(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to,
            @RequestParam(required = false) UUID device_id,
            @RequestParam(required = false) UUID module_id) {
        List<TelemetryData> data = telemetryService.getTelemetry(from, to, device_id, module_id);
        return ResponseEntity.ok(data);
    }
}
