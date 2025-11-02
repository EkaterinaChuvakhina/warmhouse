package com.warmhouse.device.controller;

import com.warmhouse.device.dto.CommandRequest;
import com.warmhouse.device.dto.UpdateDeviceRequest;
import com.warmhouse.device.entity.Device;
import com.warmhouse.device.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public ResponseEntity<List<Device>> listDevices() {
        return ResponseEntity.ok(deviceService.listDevices());
    }

    @GetMapping("/{device_id}")
    public ResponseEntity<Map<String, Object>> getDevice(@PathVariable("device_id") UUID deviceId) {
        return ResponseEntity.ok(deviceService.getDevice(deviceId));
    }

    @PatchMapping("/{device_id}")
    public ResponseEntity<Device> updateDevice(@PathVariable("device_id") UUID deviceId, @RequestBody UpdateDeviceRequest request) {
        return ResponseEntity.ok(deviceService.updateDevice(deviceId, request));
    }

    @DeleteMapping("/{device_id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("device_id") UUID deviceId) {
        deviceService.deleteDevice(deviceId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
