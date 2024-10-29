package com.xa.postransaction.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.postransaction.entities.OrderDetail;
import com.xa.postransaction.entities.OrderHeader;
import com.xa.postransaction.services.OrderDetailService;
import com.xa.postransaction.services.OrderHeaderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/orders")
@CrossOrigin("http://localhost:9002")
public class OrderController {
    
    @Autowired
    OrderHeaderService orderHeaderService;

    @Autowired
    OrderDetailService orderDetailService;

    @PostMapping("/{reference}")
    public ResponseEntity<?> saveNewOrder(@PathVariable Long reference, @RequestBody List<OrderDetail> orderDetails) {
        Double totalprice = 0.0;
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setReference(String.valueOf(reference));
        orderHeader.setTotalPrice(0.0);
        OrderHeader save = orderHeaderService.saveOrderHeader(orderHeader);

        for (OrderDetail orderDetail : orderDetails) {
            orderDetail.setHeaderId(save.getId());
            totalprice += orderDetail.getPrice();
            orderHeader.setTotalPrice(totalprice);
        }
        orderDetailService.saveAllOrders(orderDetails);
        return new ResponseEntity<>(orderDetails, HttpStatus.OK);
    }
    
}
