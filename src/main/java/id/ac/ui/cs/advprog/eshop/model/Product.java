package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private long productId;
    private String productName;
    private int productQuantity;

    public void setProductId(long id) {
        this.productId = id;
    }
}