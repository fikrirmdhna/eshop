package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

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
        
        if (voucherChecker(paymentData)) {
            this.setStatus("SUCCESS");
        } else {
            this.setStatus("REJECTED");
        }
    }

    public Payment(String id, String method, Order order, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.setOrder(order);
        this.setPaymentData(paymentData);
        this.status = PaymentStatus.PENDING.getValue();
    }

    public void setOrder(Order order) {
        if (order != null) {
            this.order = order;
        } else {
            throw new IllegalArgumentException("Order can't be null");
        }
    }

    public void setStatus(String status) {
        if(PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException("Invalid payment status");
        }
    }

    public void setPaymentData(Map<String, String> paymentData) {
        if (!PaymentMethod.contains(this.method)){
            throw new IllegalArgumentException("Can't Specify the Method");
        }
        this.paymentData = paymentData;
    }

    public boolean voucherChecker(Map<String, String> paymentData){
        String voucherCode = paymentData.get("voucherCode");

        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        if (voucherCode!=null) {
            if(voucherCode != null
                && voucherCode.length() == 16
                && voucherCode.startsWith("ESHOP")) 
            {
                int counterNum = 0; 
                for (int i = 0; i < voucherCode.length(); i++) {
                    if (Character.isDigit(voucherCode.charAt(i))) {
                        counterNum++;
                    }
                }
                if (counterNum == 8) {
                    return true;
                }
            }
        }

        if (address != null && deliveryFee != null) {
            return true;
        } 

        return false;
    }
}
