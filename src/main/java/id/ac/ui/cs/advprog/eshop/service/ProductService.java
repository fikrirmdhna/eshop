package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    Product create(Product product);
    Product edit(Product product);
    List<Product> findAll();
    void deleteProduct(String productId);
    Product getProductById(String productId);
} 
