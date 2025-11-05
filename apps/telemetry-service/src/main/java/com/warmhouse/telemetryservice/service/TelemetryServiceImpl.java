package com.warmhouse.telemetryservice.service;

import com.warmhouse.telemetryservice.entity.TelemetryData;
import com.warmhouse.telemetryservice.repository.TelemetryRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TelemetryServiceImpl implements TelemetryService {

    private final TelemetryRepository telemetryRepository;

    public TelemetryServiceImpl(TelemetryRepository telemetryRepository) {
        this.telemetryRepository = telemetryRepository;
    }

    @Override
    public void saveTelemetry(TelemetryData data) {
        telemetryRepository.save(data);
    }

    @Override
    public List<TelemetryData> getTelemetry(Instant from, Instant to, UUID deviceId, UUID moduleId) {
        return telemetryRepository.findByPeriodAndFilters(from, to, deviceId, moduleId);
    }
}