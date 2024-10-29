package com.xa.postransaction.dtos.requests;

import lombok.Data;

@Data
public class OrderHeaderRequest {
    private String reference;
    private Double totalPrice;
}
