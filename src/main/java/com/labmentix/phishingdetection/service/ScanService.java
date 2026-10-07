package com.labmentix.phishingdetection.service;

import com.labmentix.phishingdetection.model.ScanHistory;
import com.labmentix.phishingdetection.repository.ScanHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScanService {

    private final ScanHistoryRepository scanHistoryRepository;

    public ScanService(ScanHistoryRepository scanHistoryRepository) {
        this.scanHistoryRepository = scanHistoryRepository;
    }

    public ScanHistory scanUrl(Long userId, String url) {
        String riskLevel;
        String result;

        boolean suspicious = url.contains("login") || url.contains("verify")
                || url.contains("bank") || !url.startsWith("https");

        if (suspicious) {
            riskLevel = "HIGH";
            result = "Suspicious - Avoid Visiting";
        } else {
            riskLevel = "LOW";
            result = "Looks Safe";
        }

        ScanHistory scan = new ScanHistory();
        scan.setUserId(userId);
        scan.setScanType("URL");
        scan.setContent(url);
        scan.setResult(result);
        scan.setRiskLevel(riskLevel);

        return scanHistoryRepository.save(scan);
    }

    public ScanHistory scanEmail(Long userId, String emailContent) {
        String riskLevel;
        String result;

        String lower = emailContent.toLowerCase();
        boolean suspicious = lower.contains("urgent") || lower.contains("password")
                || lower.contains("click here") || lower.contains("verify your account");

        if (suspicious) {
            riskLevel = "HIGH";
            result = "Possible Phishing Content Detected";
        } else {
            riskLevel = "LOW";
            result = "No Threats Detected";
        }

        ScanHistory scan = new ScanHistory();
        scan.setUserId(userId);
        scan.setScanType("EMAIL");
        scan.setContent(emailContent);
        scan.setResult(result);
        scan.setRiskLevel(riskLevel);

        return scanHistoryRepository.save(scan);
    }

    public List<ScanHistory> getHistory(Long userId) {
        return scanHistoryRepository.findByUserId(userId);
    }
}