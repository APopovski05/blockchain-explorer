package com.andrejpopovski.blockchainexplorerapi.controller;

import com.andrejpopovski.blockchainexplorerapi.entity.Block;
import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import com.andrejpopovski.blockchainexplorerapi.service.BlockService;
import com.andrejpopovski.blockchainexplorerapi.service.TransactionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocks")
public class BlockController {

    private final BlockService blockService;
    private final TransactionService transactionService;

    public BlockController(BlockService blockService, TransactionService transactionService) {
        this.blockService = blockService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Block> getAllBlocks() {
        return blockService.getAllBlocks();
    }

    @GetMapping("/{blockNumber}")
    public Block getBlockByNumber(@PathVariable Long blockNumber) {
        return blockService.getBlockByNumber(blockNumber);
    }

    @GetMapping("/latest")
    public Block getLatestBlock() {
        return blockService.getLatestBlock();
    }

    @GetMapping("/range/{start}/{end}")
    public List<Block> getBlocksInRange(@PathVariable Long start,
                                        @PathVariable Long end) {
        return blockService.getBlocksInRange(start, end);
    }

    // ✅ NEW: Block transactions endpoint
    @GetMapping("/{blockNumber}/transactions")
    public ResponseEntity<List<Transaction>> getBlockTransactions(@PathVariable Long blockNumber) {
        return ResponseEntity.ok(transactionService.getTransactionsByBlockNumber(blockNumber));
    }
}
