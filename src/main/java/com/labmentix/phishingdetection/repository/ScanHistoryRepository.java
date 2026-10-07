package com.labmentix.phishingdetection.repository;

import com.labmentix.phishingdetection.model.ScanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScanHistoryRepository extends JpaRepository<ScanHistory, Long> {
    List<ScanHistory> findByUserId(Long userId);
}