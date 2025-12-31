package com.andrejpopovski.blockchainexplorerapi.service;

import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import com.andrejpopovski.blockchainexplorerapi.exception.ResourceNotFoundException;
import com.andrejpopovski.blockchainexplorerapi.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Transaction getTransactionByHash(String txHash) {
        return transactionRepository.findById(txHash)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found with hash: " + txHash));
    }

    public List<Transaction> getTransactionsByBlock(Long blockNumber) {
        return transactionRepository.findByBlockNumber(blockNumber);
    }

    public List<Transaction> getTransactionsByBlockNumber(Long blockNumber) {
    return transactionRepository.findByBlockNumberOrderByTransactionIndexAsc(blockNumber);
}


    public Transaction broadcastTransaction(Transaction transaction) {
        transaction.setStatus("PENDING");
        return transactionRepository.save(transaction);
    }

    public Long getMempoolSize() {
        return transactionRepository.countPendingTransactions();
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transaction> getTransactionsByAddress(String address) {
    return transactionRepository.findByAddressInvolved(address);
}



}
