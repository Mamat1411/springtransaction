package com.xa.postransaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xa.postransaction.entities.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Long>{
    
}
