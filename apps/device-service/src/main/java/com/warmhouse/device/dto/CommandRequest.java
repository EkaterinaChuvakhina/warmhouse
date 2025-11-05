package com.warmhouse.device.dto;


import lombok.Data;

import java.util.Map;

@Data
public class CommandRequest {
    private String command;
    private Map<String, Object> params;
}