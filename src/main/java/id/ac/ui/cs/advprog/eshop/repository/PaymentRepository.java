package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.ArrayList;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        for (Payment paymentCheck: paymentData){
            if (paymentCheck.getId().equals(payment.getId())){
                throw new IllegalStateException("Id is already exist");
            } 
        }
        paymentData.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment paymentCheck: paymentData){
            if (paymentCheck.getId().equals(id)){
                return paymentCheck;
            } 
        }
        return null;
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(paymentData);
    }
}
