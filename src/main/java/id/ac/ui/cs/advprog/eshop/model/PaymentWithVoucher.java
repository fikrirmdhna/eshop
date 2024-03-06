package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

public class PaymentWithVoucher extends Payment {

    public PaymentWithVoucher(String id, String method, Order order, Map<String, String> paymentData) {
        super(id, method, order, paymentData);
        if (voucherChecker(paymentData)) {
            order.setStatus(OrderStatus.SUCCESS.getValue());
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            order.setStatus(OrderStatus.FAILED.getValue());
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public boolean voucherChecker(Map<String, String> paymentData){
        String voucherCode = paymentData.get("voucherCode");
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
        return false;
    }
}
