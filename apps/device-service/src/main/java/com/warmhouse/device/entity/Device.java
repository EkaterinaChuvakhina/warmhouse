package com.warmhouse.device.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "house_id", nullable = false)
    private UUID houseId;

    @Column(name = "type_id", nullable = false)
    private UUID typeId;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "location")
    private String location;

    @Column(name = "status", nullable = false)
    private String status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}