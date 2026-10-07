package com.labmentix.phishingdetection.controller;

import com.labmentix.phishingdetection.dto.ScanEmailRequest;
import com.labmentix.phishingdetection.dto.ScanUrlRequest;
import com.labmentix.phishingdetection.model.ScanHistory;
import com.labmentix.phishingdetection.model.User;
import com.labmentix.phishingdetection.repository.UserRepository;
import com.labmentix.phishingdetection.service.ScanService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScanController {

    private final ScanService scanService;
    private final UserRepository userRepository;

    public ScanController(ScanService scanService, UserRepository userRepository) {
        this.scanService = scanService;
        this.userRepository = userRepository;
    }

    private Long getCurrentUserId(Authentication authentication) {
        String email = authentication.getName();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return user.getId();
    }

    @PostMapping("/scan/url")
    public ResponseEntity<ScanHistory> scanUrl(@RequestBody ScanUrlRequest request,
                                               Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.ok(scanService.scanUrl(userId, request.getUrl()));
    }

    @PostMapping("/scan/email")
    public ResponseEntity<ScanHistory> scanEmail(@RequestBody ScanEmailRequest request,
                                                 Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.ok(scanService.scanEmail(userId, request.getContent()));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ScanHistory>> getHistory(Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.ok(scanService.getHistory(userId));
    }
}