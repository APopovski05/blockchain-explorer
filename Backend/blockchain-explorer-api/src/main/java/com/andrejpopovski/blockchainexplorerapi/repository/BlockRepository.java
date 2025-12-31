package com.andrejpopovski.blockchainexplorerapi.repository;

import com.andrejpopovski.blockchainexplorerapi.entity.Block;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {
    Optional<Block> findByBlockHash(String blockHash);

    Optional<Block> findTopByOrderByBlockNumberDesc();

    List<Block> findByBlockNumberBetween(Long startBlock, Long endBlock);

    @Query("SELECT b FROM Block b WHERE b.miner = ?1")
    List<Block> findByMiner(String miner);
}
