package com.xa.postransaction.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.postransaction.entities.OrderHeader;
import com.xa.postransaction.repositories.OrderHeaderRepository;
import com.xa.postransaction.services.OrderHeaderService;

@Service
public class OrderHeaderServiceImpl implements OrderHeaderService{

    @Autowired
    OrderHeaderRepository orderHeaderRepository;

    @Override
    public List<OrderHeader> getAllOrderHeaders() {
        return orderHeaderRepository.findAll();
    }

    @Override
    public OrderHeader saveOrderHeader(OrderHeader orderHeader) {
        return orderHeaderRepository.save(orderHeader);
    }

    @Override
    public OrderHeader getOrderHeaderById(Long id) {
        return orderHeaderRepository.findById(id).orElse(null);
    }
    
}
