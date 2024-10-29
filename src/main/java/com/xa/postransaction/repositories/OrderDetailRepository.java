package com.xa.postransaction.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xa.postransaction.entities.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    List<OrderDetail> findByHeaderId(Long headerId);
}
