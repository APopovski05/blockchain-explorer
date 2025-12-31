package com.andrejpopovski.blockchainexplorerapi.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    private String address;

    private String label;

    private BigDecimal balance;

    private Integer transactionCount;

    private String addressType; // "EOA", "CONTRACT"

    // Default constructor
    public Address() {}

    // Constructor with parameters
    public Address(String address, BigDecimal balance, Integer transactionCount) {
        this.address = address;
        this.balance = balance;
        this.transactionCount = transactionCount;
        this.addressType = "EOA"; // Default to Externally Owned Account
    }

    // Getters and Setters for the Address Class
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public Integer getTransactionCount() { return transactionCount; }
    public void setTransactionCount(Integer transactionCount) { this.transactionCount = transactionCount; }

    public String getAddressType() { return addressType; }
    public void setAddressType(String addressType) { this.addressType = addressType; }
}
