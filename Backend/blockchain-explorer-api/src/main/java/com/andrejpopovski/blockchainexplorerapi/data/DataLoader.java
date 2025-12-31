package com.andrejpopovski.blockchainexplorerapi.data;

import com.andrejpopovski.blockchainexplorerapi.entity.Address;
import com.andrejpopovski.blockchainexplorerapi.entity.Block;
import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import com.andrejpopovski.blockchainexplorerapi.repository.AddressRepository;
import com.andrejpopovski.blockchainexplorerapi.repository.BlockRepository;
import com.andrejpopovski.blockchainexplorerapi.repository.TransactionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    private final BlockRepository blockRepository;
    private final TransactionRepository transactionRepository;
    private final AddressRepository addressRepository;

    public DataLoader(BlockRepository blockRepository,
                      TransactionRepository transactionRepository,
                      AddressRepository addressRepository) {
        this.blockRepository = blockRepository;
        this.transactionRepository = transactionRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Create sample blocks
        Block block1 = new Block(1L, "0x123abc", "0x000000", LocalDateTime.now(), 2, "0xminer1");
        block1.setGasUsed(new BigDecimal("21000"));
        block1.setGasLimit(new BigDecimal("8000000"));
        block1.setDifficulty("0x1bc16d674ec80000");
        block1.setSize(1024L);

        Block block2 = new Block(2L, "0x456def", "0x123abc", LocalDateTime.now(), 1, "0xminer2");
        block2.setGasUsed(new BigDecimal("42000"));
        block2.setGasLimit(new BigDecimal("8000000"));
        block2.setDifficulty("0x1bc16d674ec80000");
        block2.setSize(2048L);

        blockRepository.save(block1);
        blockRepository.save(block2);

        // Create sample transactions
        Transaction tx1 = new Transaction("0xtx123", 1L, "0xfrom1", "0xto1", new BigDecimal("1.5"), "SUCCESS");
        tx1.setGasPrice(new BigDecimal("20"));
        tx1.setGasUsed(21000L);
        tx1.setTransactionIndex(0);

        Transaction tx2 = new Transaction("0xtx456", 1L, "0xfrom2", "0xto2", new BigDecimal("0.8"), "SUCCESS");
        tx2.setGasPrice(new BigDecimal("25"));
        tx2.setGasUsed(21000L);
        tx2.setTransactionIndex(1);

        transactionRepository.save(tx1);
        transactionRepository.save(tx2);

        // Create sample addresses
        Address addr1 = new Address("0xfrom1", new BigDecimal("10.5"), 5);
        addr1.setLabel("Alice's Wallet");

        Address addr2 = new Address("0xto1", new BigDecimal("25.7"), 12);
        addr2.setLabel("Bob's Wallet");

        addressRepository.save(addr1);
        addressRepository.save(addr2);
    }
}
