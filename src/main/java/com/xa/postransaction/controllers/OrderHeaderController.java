package com.xa.postransaction.controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xa.postransaction.dtos.requests.OrderHeaderRequest;
import com.xa.postransaction.dtos.responses.OrderHeaderResponse;
import com.xa.postransaction.entities.OrderHeader;
import com.xa.postransaction.services.OrderHeaderService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/orderheader")
@CrossOrigin("http://localhost:9002")
public class OrderHeaderController {
    
    @Autowired
    OrderHeaderService orderHeaderService;

    @GetMapping("")
    public ResponseEntity<?> getAllOrderHeaders() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            List<OrderHeader> orderHeaders = orderHeaderService.getAllOrderHeaders();
            List<OrderHeaderResponse> response = orderHeaders.stream().map(orderHeader -> modelMapper.map(orderHeader, OrderHeaderResponse.class)).collect(Collectors.toList());
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", response);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("")
    public ResponseEntity<?> saveOrderHeader() {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        try {
            OrderHeaderRequest request = new OrderHeaderRequest();
            request.setReference(String.valueOf(System.currentTimeMillis()));
            request.setTotalPrice(0.0);
            OrderHeader orderHeader = modelMapper.map(request, OrderHeader.class);
            orderHeaderService.saveOrderHeader(orderHeader);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", orderHeader);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateOrderHeader(@PathVariable Long id, @RequestBody OrderHeaderRequest orderHeaderRequest) {
        LinkedHashMap<String, Object> resultMap = new LinkedHashMap<>();
        try {
            OrderHeader orderHeader = orderHeaderService.getOrderHeaderById(id);
            orderHeader.setTotalPrice(orderHeaderRequest.getTotalPrice());
            orderHeaderService.saveOrderHeader(orderHeader);
            resultMap.put("status", "200");
            resultMap.put("message", "success");
            resultMap.put("data", orderHeader);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            resultMap.put("status", "500");
            resultMap.put("message", "failed");
            resultMap.put("error", e);
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
