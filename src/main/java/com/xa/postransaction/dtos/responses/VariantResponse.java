package com.xa.postransaction.dtos.responses;

import java.time.LocalDateTime;

import com.xa.postransaction.entities.Product;

import lombok.Data;

@Data
public class VariantResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Product product;
    private Long productId;
    private Long price;
    private Integer stock;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
