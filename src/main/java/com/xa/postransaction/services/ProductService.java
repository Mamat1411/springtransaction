package com.xa.postransaction.services;

import java.util.List;

import com.xa.postransaction.entities.Product;

public interface ProductService {
    List<Product> getAllProducts();
    void saveProduct(Product product);
    Product getProductById(Long id);
    void deleteProduct(Long id);
    List<Product> getProductsByCategoryId(Long id);
}
