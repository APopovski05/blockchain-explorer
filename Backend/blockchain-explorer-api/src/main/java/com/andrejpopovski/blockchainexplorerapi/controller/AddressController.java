package com.andrejpopovski.blockchainexplorerapi.controller;

import com.andrejpopovski.blockchainexplorerapi.entity.Address;
import com.andrejpopovski.blockchainexplorerapi.entity.Transaction;
import com.andrejpopovski.blockchainexplorerapi.service.AddressService;
import com.andrejpopovski.blockchainexplorerapi.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    private final AddressService addressService;
    private final TransactionService transactionService;

    public AddressController(AddressService addressService, TransactionService transactionService) {
        this.addressService = addressService;
        this.transactionService = transactionService;
    }

    @GetMapping("/{address}")
    public Address getAddressDetails(@PathVariable String address) {
        return addressService.getAddressByAddress(address);
    }

    @PatchMapping("/{address}/label")
    public Address updateAddressLabel(@PathVariable String address, @RequestParam String label) {
        return addressService.updateAddressLabel(address, label);
    }

    @GetMapping("/{address}/transactions")
    public List<Transaction> getAddressTransactions(@PathVariable String address) {
        return transactionService.getTransactionsByAddress(address);
    }
}
