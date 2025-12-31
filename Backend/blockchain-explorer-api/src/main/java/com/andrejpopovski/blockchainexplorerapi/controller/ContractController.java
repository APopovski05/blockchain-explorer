package com.andrejpopovski.blockchainexplorerapi.controller;

import com.andrejpopovski.blockchainexplorerapi.entity.ContractWatch;
import com.andrejpopovski.blockchainexplorerapi.service.ContractService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contracts")
public class ContractController {
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping("/{contractAddress}/watch")
    public ContractWatch subscribeToContract(@PathVariable String contractAddress,
                                             @RequestParam String eventName) {
        return contractService.subscribeToContract(contractAddress, eventName);
    }

    @DeleteMapping("/watches/{watchId}")
    public void unsubscribeFromWatch(@PathVariable Long watchId) {
        contractService.unsubscribeFromWatch(watchId);
    }

    @GetMapping("/watches")
    public List<ContractWatch> getAllWatches() {
        return contractService.getAllWatches();
    }
}
