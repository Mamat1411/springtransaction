package com.xa.postransaction.dtos.requests;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String slug;
    private String description;
    private Long categoryId;
}
