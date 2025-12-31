package com.andrejpopovski.blockchainexplorerapi.repository;

import com.andrejpopovski.blockchainexplorerapi.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {
    List<Address> findByAddressType(String addressType);

    List<Address> findByLabelContaining(String label);
}
