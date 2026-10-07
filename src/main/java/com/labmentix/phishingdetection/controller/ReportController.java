package com.labmentix.phishingdetection.controller;

import com.labmentix.phishingdetection.model.Report;
import com.labmentix.phishingdetection.service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public ResponseEntity<List<Report>> getReports() {
        return ResponseEntity.ok(reportService.getAllReports());
    }
}