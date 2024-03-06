package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentWithCOD extends Payment {
    
    public PaymentWithCOD(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
        if (cashOnDeliveryChecker(paymentData)) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            order.setStatus(OrderStatus.FAILED.getValue());
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public boolean cashOnDeliveryChecker(Map<String, String> paymentData){
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (address != null && deliveryFee != null) {
            if (!(address.isBlank() && deliveryFee.isBlank())) {
                return true;
            } 
        }
        return false;
    }
}
