package com.xa.postransaction.dtos.requests;

import lombok.Data;

@Data
public class VariantRequest {
    private String name;
    private String slug;
    private String description;
    private Long productId;
    private Long price;
    private Integer stock;
}
