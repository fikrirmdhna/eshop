package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.PaymentWithCOD;
import id.ac.ui.cs.advprog.eshop.model.PaymentWithVoucher;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> paymentList;

    @BeforeEach
    void setup() {
        paymentRepository = new PaymentRepository();
        paymentList = new ArrayList<>();
        
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("136522556-012a-4c07-b546-54eb1396d79b", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
            products, 1708570000L, "Safira Sudrajat");
        orders.add(order2);
        Order order3 = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e",
            products, 1708570000L, "Bambang Sudrajat");
        orders.add(order3);

        Map<String, String> paymentDataVoucher  = new HashMap<>();
        paymentDataVoucher.put("voucherCode", "ESHOP1234ABC5678");

        PaymentWithVoucher voucher = new PaymentWithVoucher("4084c620-013b-4415-b086-08f7b089408d", PaymentMethod.VOUCHER.getValue(), orders.get(0), paymentDataVoucher);
        paymentList.add(voucher);

        Map<String, String> paymentDataCashOnDelivery = new HashMap<>();
        paymentDataCashOnDelivery.put("address", "Universitas Indonesia");
        paymentDataCashOnDelivery.put("deliveryFee", "2000");

        PaymentWithCOD cashOnDelivery = new PaymentWithCOD("ec556e97-10a5-4d48-a068-d45c6fca71c1", PaymentMethod.COD.getValue(), orders.get(0), paymentDataCashOnDelivery);
        paymentList.add(cashOnDelivery);
    }

    @Test
    void testAddPaymentSuccess() {
        Payment payment = paymentList.get(1);
        Payment res = paymentRepository.save(payment);

        Payment getResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), getResult.getId());
        assertEquals(payment.getId(), res.getId());
        assertEquals(payment.getMethod(), getResult.getMethod());
        assertEquals(payment.getPaymentData(), getResult.getPaymentData());
        assertEquals(payment.getStatus(), getResult.getStatus());
    }

    @Test
    void testDuplicatePayment() {
        Payment payment1 = paymentList.get(1);
        paymentRepository.save(payment1);

        Payment payment2 = new Payment(payment1.getId(), payment1.getMethod(), payment1.getOrder(), payment1.getPaymentData());
        payment2.setStatus(PaymentStatus.PENDING.getValue());

        assertThrows(IllegalStateException.class,()->{
            paymentRepository.save(payment2);
        });
    }

    @Test
    void testPaymentIdIsFound() {
        for (Payment pay: paymentList) {
            paymentRepository.save(pay);
        }
        
        Payment getResult = paymentRepository.findById(paymentList.get(1).getId());
        assertEquals(paymentList.get(1).getId(), getResult.getId());
        assertEquals(paymentList.get(1).getMethod(), getResult.getMethod());
        assertEquals(paymentList.get(1).getPaymentData(), getResult.getPaymentData());
        assertSame(paymentList.get(1).getOrder(), getResult.getOrder());
        assertNotNull(getResult);
    }

    @Test
    void testPaymentIdIsNotFound() {
        for (Payment pay: paymentList) {
            paymentRepository.save(pay);
        }
        
        Payment getResult = paymentRepository.findById("iniGaAdaIdnya");
        assertNull(getResult);
    }

    @Test
    void testGetAllPaymentList() {
        for (Payment pay: paymentList) {
            paymentRepository.save(pay);
        }

        List<Payment> getPaymentList = paymentRepository.getAllPayments();
        assertEquals(paymentList.size(), getPaymentList.size());
    }
}
