package com.xa.postransaction.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa.postransaction.entities.OrderDetail;
import com.xa.postransaction.repositories.OrderDetailRepository;
import com.xa.postransaction.services.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService{

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetail> getAllByOrderHeaderId(Long id) {
        return this.orderDetailRepository.findByHeaderId(id);
    }

    @Override
    public OrderDetail saveOrderDetail(OrderDetail orderDetail) {
        return this.orderDetailRepository.save(orderDetail);
    }

    @Override
    public void deleteOrderDetail(Long id) {
        OrderDetail orderDetail = this.orderDetailRepository.findById(id).orElse(null);
        this.orderDetailRepository.deleteById(orderDetail.getId());
    }

    @Override
    public List<OrderDetail> saveAllOrders(List<OrderDetail> orderDetails) {
        return orderDetailRepository.saveAll(orderDetails);
    }
    
}
