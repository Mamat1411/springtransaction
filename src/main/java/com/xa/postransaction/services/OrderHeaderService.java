package com.xa.postransaction.services;

import java.util.List;

import com.xa.postransaction.entities.OrderHeader;

public interface OrderHeaderService {
    List<OrderHeader> getAllOrderHeaders();
    OrderHeader saveOrderHeader(OrderHeader orderHeader);
    OrderHeader getOrderHeaderById(Long id);
}
