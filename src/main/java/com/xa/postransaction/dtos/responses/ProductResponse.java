package com.xa.postransaction.dtos.responses;

import java.time.LocalDateTime;

import com.xa.postransaction.entities.Category;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String slug;
    private String description;
    private Category category;
    private Long categoryId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String createdBy;
    private String updatedBy;
}
