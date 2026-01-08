package com.major.connect.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.major.connect.models.Carrier;
import java.util.List;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long>{
	Optional<Carrier>findByCarrierId(long carrierId);
	List<Carrier> findByCarrierName(String carrierName);
	boolean deleteByCarrierId(long carrierId);
}
