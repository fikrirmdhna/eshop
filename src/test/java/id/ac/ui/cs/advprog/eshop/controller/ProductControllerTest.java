package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CarServiceImpl carservice;

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/product/create"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("CreateProduct"));
    }

    @Test
    public void testCreateProductPost() throws Exception {
        Product product = new Product();
        when(productService.create(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/create")
                .param("productName", "Test Product")
                .param("productDescription", "Test Description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    @Test
    public void testEditProductPage() throws Exception {
        String productId = "123";
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.get("/product/edit/{productId}", productId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("EditProduct"))
                .andExpect(MockMvcResultMatchers.model().attribute("product", product));
    }

    @Test
    public void testEditProductPost() throws Exception {
        String productId = "123";
        Product product = new Product();
        product.setProductId(productId);

        when(productService.edit(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/product/edit/{productId}", productId)
                .param("productName", "Test Product")
                .param("productDescription", "Test Description")
                .param("productId", productId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("../list"));

        verify(productService, times(1)).edit(any(Product.class));
    }

    @Test
    public void testProductListPage() throws Exception {
        when(productService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/product/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("ProductList"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", Collections.emptyList()));
    }

    @Test
    public void testDeleteProductPost() throws Exception {
        String productId = "123";

        mockMvc.perform(MockMvcRequestBuilders.get("/product/{productId}", productId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("list"));

        verify(productService, times(1)).deleteProduct(productId);
    }
}
