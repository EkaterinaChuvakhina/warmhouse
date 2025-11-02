package com.warmhouse.temperatureservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Builder
public class TemperatureDto {
    @JsonProperty("value")
    private BigDecimal value;
    @JsonProperty("unit")
    private String unit;
    @JsonProperty("timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime timestamp;
    @JsonProperty("location")
    private String location;
    @JsonProperty("status")
    private String status;
    @JsonProperty("sensor_id")
    private String sensorId;
    @JsonProperty("sensor_type")
    private String sensorType;
    @JsonProperty("description")
    private String description;
}