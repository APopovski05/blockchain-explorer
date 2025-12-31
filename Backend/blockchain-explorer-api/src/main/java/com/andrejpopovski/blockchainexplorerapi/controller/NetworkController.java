package com.andrejpopovski.blockchainexplorerapi.controller;

import com.andrejpopovski.blockchainexplorerapi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/network")
public class NetworkController {
    private final TransactionService transactionService;

    public NetworkController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/metrics")
    public Map<String, Object> getNetworkMetrics() {
        Map<String, Object> metrics = new HashMap<>();
        metrics.put("mempoolSize", transactionService.getMempoolSize());
        metrics.put("totalTransactions", transactionService.getAllTransactions().size());
        return metrics;
    }

    @GetMapping("/mempool")
    public Map<String, Object> getMempoolStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("pendingTransactions", transactionService.getMempoolSize());
        status.put("status", "active");
        return status;
    }

    @GetMapping("/fees/estimate")
    public Map<String, Object> getFeeEstimate() {
        Map<String, Object> fees = new HashMap<>();
        fees.put("slow", "10 gwei");
        fees.put("standard", "20 gwei");
        fees.put("fast", "30 gwei");
        return fees;
    }
}
