package com.andrejpopovski.blockchainexplorerapi.service;

import com.andrejpopovski.blockchainexplorerapi.entity.Block;
import com.andrejpopovski.blockchainexplorerapi.exception.ResourceNotFoundException;
import com.andrejpopovski.blockchainexplorerapi.repository.BlockRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlockService {

    private final BlockRepository blockRepository;

    public BlockService(BlockRepository blockRepository) {
        this.blockRepository = blockRepository;
    }

    public Block getBlockByNumber(Long blockNumber) {
        return blockRepository.findById(blockNumber)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Block not found with number: " + blockNumber));
    }

    public Block getLatestBlock() {
        return blockRepository.findTopByOrderByBlockNumberDesc()
                .orElseThrow(() ->
                        new ResourceNotFoundException("No blocks found"));
    }

    public List<Block> getBlocksInRange(Long startBlock, Long endBlock) {
        return blockRepository.findByBlockNumberBetween(startBlock, endBlock);
    }

    public List<Block> getAllBlocks() {
        return blockRepository.findAll(Sort.by(Sort.Direction.DESC, "blockNumber"));
    }

    public Block saveBlock(Block block) {
        return blockRepository.save(block);
    }
}
