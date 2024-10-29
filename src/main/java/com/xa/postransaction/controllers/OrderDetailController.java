package com.xa.postransaction.controllers;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.postransaction.entities.OrderDetail;
import com.xa.postransaction.services.OrderDetailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/orderdetail")
@CrossOrigin("http://localhost:9002")
public class OrderDetailController {
    
    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderByHeaderId(@PathVariable("id") Long id) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            List<OrderDetail> orderDetails = this.orderDetailService.getAllByOrderHeaderId(id);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", orderDetails);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<?> saveNewOrderDetail(@PathVariable("id") Long id, @RequestBody OrderDetail orderDetail) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            this.orderDetailService.saveOrderDetail(orderDetail);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", orderDetail);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }
}
