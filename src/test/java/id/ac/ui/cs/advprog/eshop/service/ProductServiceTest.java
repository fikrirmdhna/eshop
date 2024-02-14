package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");

        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);

        verify(productRepository, times(1)).create(product);
    }

    @Test
    public void testEditProduct() {
        Product existingProduct = new Product();
        existingProduct.setProductId("123");
        existingProduct.setProductName("Existing Product");

        Product updatedProduct = new Product();
        updatedProduct.setProductId("123");
        updatedProduct.setProductName("Updated Product");

        when(productRepository.findProductById("123")).thenReturn(existingProduct);
        when(productRepository.edit(existingProduct, updatedProduct)).thenReturn(updatedProduct);

        Product editedProduct = productService.edit(updatedProduct);
        assertEquals(updatedProduct, editedProduct);

        verify(productRepository, times(1)).findProductById("123");
        verify(productRepository, times(1)).edit(existingProduct, updatedProduct);
    }

    @Test
    public void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        Iterator<Product> iterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> retrievedProducts = productService.findAll();
        assertEquals(productList.size(), retrievedProducts.size());

        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteProduct() {
        String productId = "123";

        productService.deleteProduct(productId);

        verify(productRepository, times(1)).deleteProduct(productId);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product();
        product.setProductId("123");

        when(productRepository.findProductById("123")).thenReturn(product);

        Product retrievedProduct = productService.getProductById("123");
        assertEquals(product, retrievedProduct);

        verify(productRepository, times(1)).findProductById("123");
    }
}
