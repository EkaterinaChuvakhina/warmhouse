package com.warmhouse.telemetryservice.service;

import com.warmhouse.telemetryservice.entity.TelemetryData;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface TelemetryService {
    void saveTelemetry(TelemetryData data);

    List<TelemetryData> getTelemetry(Instant from, Instant to, UUID deviceId, UUID moduleId);
}