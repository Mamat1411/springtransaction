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

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_details")
public class OrderDetail extends BaseEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "header_id", insertable = false, updatable = false)
    public OrderHeader orderHeader;

    @Column(name = "header_id")
    private Long headerId;

    @ManyToOne
    @JoinColumn(name = "variant_id", insertable = false, updatable = false)
    public Variant variant;

    @Column(name = "variant_id")
    private Long variantId;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "price")
    private Long price;
}
