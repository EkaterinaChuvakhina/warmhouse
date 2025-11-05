package com.warmhouse.device.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @Column(name = "device_id", nullable = false)
    private UUID deviceId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "status", nullable = false)
    private String status;
}