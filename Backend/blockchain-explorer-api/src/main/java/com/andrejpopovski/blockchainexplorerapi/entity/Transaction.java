package com.andrejpopovski.blockchainexplorerapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    private String txHash;

    @NotNull
    private Long blockNumber;

    @NotNull
    private String fromAddress;

    @NotNull
    private String toAddress;

    @NotNull
    @Column(name = "amount") // Renamed from 'value' to 'amount'
    private BigDecimal amount;

    private BigDecimal gasPrice;

    private Long gasUsed;

    @NotNull
    private String status; // "SUCCESS", "FAILED", "PENDING"

    private LocalDateTime timestamp;

    private Integer transactionIndex;

    // Default constructor
    public Transaction() {}

    // Constructor with parameters
    public Transaction(String txHash, Long blockNumber, String fromAddress,
                       String toAddress, BigDecimal amount, String status) {
        this.txHash = txHash;
        this.blockNumber = blockNumber;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.amount = amount;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and Setters
    public String getTxHash() { return txHash; }
    public void setTxHash(String txHash) { this.txHash = txHash; }

    public Long getBlockNumber() { return blockNumber; }
    public void setBlockNumber(Long blockNumber) { this.blockNumber = blockNumber; }

    public String getFromAddress() { return fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

    public String getToAddress() { return toAddress; }
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public BigDecimal getGasPrice() { return gasPrice; }
    public void setGasPrice(BigDecimal gasPrice) { this.gasPrice = gasPrice; }

    public Long getGasUsed() { return gasUsed; }
    public void setGasUsed(Long gasUsed) { this.gasUsed = gasUsed; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public Integer getTransactionIndex() { return transactionIndex; }
    public void setTransactionIndex(Integer transactionIndex) { this.transactionIndex = transactionIndex; }
}
