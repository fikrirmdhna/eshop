package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    private static long counter = 0;

    public Product create(Product product) {
        productData.add(product);
        product.setProductId(++counter);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findProductById(long id) {
        for (Product product : productData) {
            if (product.getProductId() == id) {
                return product;
            }
        }
        return null;
    }

    public void deleteProduct(long id) {
        Product product = findProductById(id);
        productData.remove(product);
    }
}
