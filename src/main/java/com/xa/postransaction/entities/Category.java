package com.xa.postransaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "categories")
@NoArgsConstructor
public class Category extends BaseEntity{

    public Category(String name, String slug, String description){
        this.name = name;
        this.slug = slug;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "slug", length = 50)
    private String slug;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
