package com.andrejpopovski.blockchainexplorerapi.controller;

import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import com.andrejpopovski.blockchainexplorerapi.service.TransactionService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // ✅ Get transaction by hash
    @GetMapping("/{txHash}")
    public Transaction getTransactionByHash(@PathVariable String txHash) {
        return transactionService.getTransactionByHash(txHash);
    }

    // ✅ Get transactions by block (existing endpoint)
    @GetMapping("/block/{blockNumber}")
    public List<Transaction> getTransactionsByBlock(@PathVariable Long blockNumber) {
        return transactionService.getTransactionsByBlock(blockNumber);
    }

    // ✅ NEW: Search transactions
    // /api/transactions
    // /api/transactions?blockNumber=2
    // /api/transactions?address=0xabc...
    @GetMapping
    public List<Transaction> searchTransactions(
            @RequestParam(required = false) Long blockNumber,
            @RequestParam(required = false) String address
    ) {
        if (blockNumber != null) {
            return transactionService.getTransactionsByBlock(blockNumber);
        }

        if (address != null && !address.isBlank()) {
            return transactionService.getTransactionsByAddress(address);
        }

        return transactionService.getAllTransactions();
    }

    // ✅ Broadcast transaction (extra feature)
    @PostMapping("/broadcast")
    public Transaction broadcastTransaction(@RequestBody Transaction transaction) {
        return transactionService.broadcastTransaction(transaction);
    }
}
