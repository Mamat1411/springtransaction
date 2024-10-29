package com.xa.postransaction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xa.postransaction.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
    
}
