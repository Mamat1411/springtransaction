package com.xa.postransaction.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "products")
@NoArgsConstructor
public class Product extends BaseEntity{

    public Product(String name, String slug, String description, Long categoryId){
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.categoryId = categoryId;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    public Category category;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "slug", length = 50)
    private String slug;
}
