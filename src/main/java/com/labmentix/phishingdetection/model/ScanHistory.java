package com.labmentix.phishingdetection.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "scan_history")
@Data
public class ScanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "scan_type", nullable = false)
    private String scanType; // "URL" or "EMAIL"

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String result;

    @Column(name = "risk_level", nullable = false)
    private String riskLevel; // "LOW", "MEDIUM", "HIGH"

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}