package com.andrejpopovski.blockchainexplorerapi.service;

import com.andrejpopovski.blockchainexplorerapi.entity.ContractWatch;
import com.andrejpopovski.blockchainexplorerapi.exception.ResourceNotFoundException;
import com.andrejpopovski.blockchainexplorerapi.repository.ContractWatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    private final ContractWatchRepository contractWatchRepository;

    public ContractService(ContractWatchRepository contractWatchRepository) {
        this.contractWatchRepository = contractWatchRepository;
    }

    public ContractWatch subscribeToContract(String contractAddress, String eventName) {
        ContractWatch watch = new ContractWatch(contractAddress, eventName);
        return contractWatchRepository.save(watch);
    }

    public void unsubscribeFromWatch(Long watchId) {
        if (!contractWatchRepository.existsById(watchId)) {
            throw new ResourceNotFoundException("Watch not found with ID: " + watchId);
        }
        contractWatchRepository.deleteById(watchId);
    }

    public List<ContractWatch> getActiveWatches() {
        return contractWatchRepository.findByIsActive(true);
    }

    public List<ContractWatch> getAllWatches() {
        return contractWatchRepository.findAll();
    }
}
