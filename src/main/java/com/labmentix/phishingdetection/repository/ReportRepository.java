package com.labmentix.phishingdetection.repository;

import com.labmentix.phishingdetection.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}