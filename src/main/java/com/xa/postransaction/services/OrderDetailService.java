package com.xa.postransaction.services;

import java.util.List;

import com.xa.postransaction.entities.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getAllByOrderHeaderId(Long id);
    OrderDetail saveOrderDetail(OrderDetail orderDetail);
    List<OrderDetail> saveAllOrders(List<OrderDetail> orderDetails);
    void deleteOrderDetail(Long id);
}
