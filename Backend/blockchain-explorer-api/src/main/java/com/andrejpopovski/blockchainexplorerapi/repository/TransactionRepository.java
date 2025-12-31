package com.andrejpopovski.blockchainexplorerapi.repository;

import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findByBlockNumber(Long blockNumber);

    List<Transaction> findByFromAddress(String fromAddress);

    List<Transaction> findByToAddress(String toAddress);

    @Query("SELECT t FROM Transaction t WHERE t.fromAddress = ?1 OR t.toAddress = ?1")
    List<Transaction> findByAddressInvolved(String address);

    List<Transaction> findByStatus(String status);

    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.status = 'PENDING'")
    Long countPendingTransactions();

    List<Transaction> findByBlockNumberOrderByTransactionIndexAsc(Long blockNumber);

}
