package com.andrejpopovski.blockchainexplorerapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "blocks")
public class Block {
    @Id
    private Long blockNumber;

    @NotNull
    @Column(unique = true)
    private String blockHash;

    private String parentHash;

    @NotNull
    private LocalDateTime timestamp;

    private Integer transactionCount;

    private String miner;

    private BigDecimal gasUsed;

    private BigDecimal gasLimit;

    private String difficulty;

    private Long size;

    // Default constructor
    public Block() {}

    // Constructor with parameters

    public Block(Long blockNumber, String blockHash, String parentHash,
                 LocalDateTime timestamp, Integer transactionCount, String miner) {
        this.blockNumber = blockNumber;
        this.blockHash = blockHash;
        this.parentHash = parentHash;
        this.timestamp = timestamp;
        this.transactionCount = transactionCount;
        this.miner = miner;
    }

    // Getters and Setters for the Block Class

    public Long getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Long blockNumber) { this.blockNumber = blockNumber; }

    public String getBlockHash() { return blockHash; }
    public void setBlockHash(String blockHash) { this.blockHash = blockHash; }

    public String getParentHash() { return parentHash; }
    public void setParentHash(String parentHash) { this.parentHash = parentHash; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Integer getTransactionCount() { return transactionCount; }
    public void setTransactionCount(Integer transactionCount) { this.transactionCount = transactionCount; }

    public String getMiner() { return miner; }
    public void setMiner(String miner) { this.miner = miner; }

    public BigDecimal getGasUsed() { return gasUsed; }
    public void setGasUsed(BigDecimal gasUsed) { this.gasUsed = gasUsed; }

    public BigDecimal getGasLimit() { return gasLimit; }
    public void setGasLimit(BigDecimal gasLimit) { this.gasLimit = gasLimit; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }
}
