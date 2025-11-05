package com.warmhouse.telemetryservice.entity;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;
import lombok.Data;


import java.time.Instant;
import java.util.UUID;

@Data
@Measurement(name = "telemetry_data")
public class TelemetryData {
    @Column(name = "telemetry_id", tag = true)
    private UUID telemetryId = UUID.randomUUID();

    @Column(name = "device_id", tag = true)
    private UUID deviceId;

    @Column(name = "module_id", tag = true)
    private UUID moduleId;

    @Column(name = "type", tag = true)
    private String type;

    @Column(name = "value")
    private Double value;

    @Column(name = "time")
    private Instant timestamp = Instant.now();
}
