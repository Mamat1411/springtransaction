package com.xa.postransaction.dtos.requests;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String slug;
    private String description;
}
