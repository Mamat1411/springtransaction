package com.xa.postransaction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.xa.postransaction.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
    
    @Query(value = "select * from products where is_deleted = false", nativeQuery = true)
    List<Product> findByIsDeleted();

    List<Product> findByCategoryId(Long categoryId);
}
