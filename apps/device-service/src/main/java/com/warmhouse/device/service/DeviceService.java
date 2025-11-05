package com.warmhouse.device.service;

import com.warmhouse.device.dto.CommandRequest;
import com.warmhouse.device.dto.UpdateDeviceRequest;
import com.warmhouse.device.entity.Device;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DeviceService {
    List<Device> listDevices();

    Map<String, Object> getDevice(UUID deviceId);

    Device updateDevice(UUID deviceId, UpdateDeviceRequest request);

    void deleteDevice(UUID deviceId);

}
