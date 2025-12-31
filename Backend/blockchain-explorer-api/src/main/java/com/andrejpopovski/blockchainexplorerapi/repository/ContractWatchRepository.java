package com.andrejpopovski.blockchainexplorerapi.repository;

import com.andrejpopovski.blockchainexplorerapi.entity.ContractWatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractWatchRepository extends JpaRepository<ContractWatch, Long> {
    List<ContractWatch> findByContractAddress(String contractAddress);

    List<ContractWatch> findByIsActive(Boolean isActive);

    List<ContractWatch> findByContractAddressAndEventName(String contractAddress, String eventName);
}
