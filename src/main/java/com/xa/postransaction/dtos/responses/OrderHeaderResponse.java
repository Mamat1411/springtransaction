package com.xa.postransaction.dtos.responses;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OrderHeaderResponse {
    private Long id;
    private String reference;
    private Double totalPrice;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime updatedAt;
    private String updatedBy;
    private String isDeleted;
}
