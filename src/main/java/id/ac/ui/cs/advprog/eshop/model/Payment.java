package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Order order;
    Map<String, String> paymentData;

    public Payment(String id, String method, Order order, Map<String, String> paymentData, String status) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.setStatus(status);
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.status = "PENDING";
    }

    public void setOrder(Order order) {
        if (order != null) {
            this.order = order;
        } else {
            throw new IllegalArgumentException("Order can't be null");
        }
    }

    public void setStatus(String status) {
        List<String> statusList = Arrays.asList("PENDING", "SUCCESS", "REJECTED");

        if(statusList.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        this.paymentData = null;
    }
}
