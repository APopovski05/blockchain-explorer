package com.andrejpopovski.blockchainexplorerapi.service;

import com.andrejpopovski.blockchainexplorerapi.entity.Address;
import com.andrejpopovski.blockchainexplorerapi.exception.ResourceNotFoundException;
import com.andrejpopovski.blockchainexplorerapi.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address getAddressByAddress(String address) {
        return addressRepository.findById(address)
                .orElseThrow(() -> new ResourceNotFoundException("Address not found: " + address));
    }

    public Address updateAddressLabel(String address, String label) {
        Address addr = getAddressByAddress(address);
        addr.setLabel(label);
        return addressRepository.save(addr);
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }
}
