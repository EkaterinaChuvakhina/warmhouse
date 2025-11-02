package com.warmhouse.device.dto;

import lombok.Data;

@Data
public class UpdateDeviceRequest {
    private String name;
    private String location;
}