package com.andrejpopovski.blockchainexplorerapi.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contract_watches")
public class ContractWatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long watchId;

    @Column(nullable = false)
    private String contractAddress;

    @Column(nullable = false)
    private String eventName;

    @Column(nullable = false)
    private Boolean isActive;

    private LocalDateTime createdAt;

    private String subscriberInfo;

    // Default constructor
    public ContractWatch() {
        this.createdAt = LocalDateTime.now();
        this.isActive = true;
    }

    // Constructor with parameters
    public ContractWatch(String contractAddress, String eventName) {
        this();
        this.contractAddress = contractAddress;
        this.eventName = eventName;
    }

    // Getters and Setters for the ContractWatch class
    public Long getWatchId() { return watchId; }
    public void setWatchId(Long watchId) { this.watchId = watchId; }

    public String getContractAddress() { return contractAddress; }
    public void setContractAddress(String contractAddress) { this.contractAddress = contractAddress; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public String getSubscriberInfo() { return subscriberInfo; }
    public void setSubscriberInfo(String subscriberInfo) { this.subscriberInfo = subscriberInfo; }
}
