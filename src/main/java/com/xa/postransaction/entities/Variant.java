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
@Table(name = "variants")
@NoArgsConstructor
public class Variant extends BaseEntity{

    public Variant(String name, String slug, String description, Long productId, Integer stock, Long price){
        this.name = name;
        this.slug = slug;
        this.description = description;
        this.productId = productId;
        this.stock = stock;
        this.price = price;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    public Product product;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "description")
    private String description;

    @Column(name = "slug", length = 50)
    private String slug;

    @Column(name = "price")
    private Long price;

    @Column(name = "stock")
    private Integer stock;
}
