package com.warmhouse.device.service;

import com.warmhouse.device.dto.UpdateDeviceRequest;
import com.warmhouse.device.entity.Device;
import com.warmhouse.device.entity.Module;
import com.warmhouse.device.repository.DeviceRepository;
import com.warmhouse.device.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final ModuleRepository moduleRepository;

    public DeviceServiceImpl(DeviceRepository deviceRepository, ModuleRepository moduleRepository) {
        this.deviceRepository = deviceRepository;
        this.moduleRepository = moduleRepository;
    }

    @Override
    public List<Device> listDevices() {
        UUID userId = getCurrentUserId();
        return deviceRepository.findByHouseId(userId);
    }

    @Override
    public Map<String, Object> getDevice(UUID deviceId) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
        List<Module> modules = moduleRepository.findByDeviceId(deviceId);
        Map<String, Object> response = new HashMap<>();
        response.put("device", device);
        response.put("modules", modules);
        return response;
    }

    @Override
    public Device updateDevice(UUID deviceId, UpdateDeviceRequest request) {
        Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new RuntimeException("Device not found"));
        if (request.getName() != null) {
            device.setName(request.getName());
        }
        if (request.getLocation() != null) {
            device.setLocation(request.getLocation());
        }
        return deviceRepository.save(device);
    }

    @Override
    public void deleteDevice(UUID deviceId) {
        deviceRepository.deleteById(deviceId);
    }

    private UUID getCurrentUserId() {
        return UUID.randomUUID();
    }
}