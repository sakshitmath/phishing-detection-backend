package com.labmentix.phishingdetection.dto;

import lombok.Data;

@Data
public class ScanEmailRequest {
    private String content;
}