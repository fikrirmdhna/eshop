package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

class PaymentTest {
    Map<String, String> paymentData;
    List<Product> products;
    Order order;

    @BeforeEach
    void setup() {
        this.paymentData = new HashMap<>();
        this.products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);

        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8620155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);

        this.products.add(product1);
        this.products.add(product2);

        order = new Order("eb558e9f-1c39-460e-8860-71af6af63bd6", 
             products, 1708560000L, "Bambang Suryanto");
    }

    @Test
    void testCreatePaymentWithNullOrder() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
                 "", null, paymentData);
        });
    }

    @Test
    void testCreatePaymentVoucherSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherWaitingStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData);
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.PENDING.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
                "", order, paymentData, "IniKodeVoucherAbal");
        });

        paymentData.clear();
    }

    @Test
    void testCreatePaymentVoucherNullStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
                "", order, paymentData, null);
        });

        paymentData.clear();
    }

    @Test
    void testCreateCashOnDeliveryPaymentSuccessStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData, PaymentStatus.SUCCESS.getValue());
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateCashOnDeliveryPaymentPendingStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData);
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.PENDING.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreateCashOnDeliveryPaymentRejectedStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
        "", order, paymentData, PaymentStatus.REJECTED.getValue());
        assertNull(testPayment.getPaymentData());
        assertSame(testPayment.getOrder(), order);
        assertEquals("", testPayment.getMethod());
        assertEquals("e6e60d39-41fb-4ff0-8631-3491e483c180", testPayment.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryInvalidStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
                "", order, paymentData, "IniTestAja");
        });

        paymentData.clear();
    }

    @Test
    void testCreatePaymentCashOnDeliveryNullStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
                "", order, paymentData, null);
        });

        paymentData.clear();
    }

    @Test
    void testPaymentWithVoucherSetSuccessStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);
        testPayment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testPaymentWithVoucherSetRejectedStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABCD5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);
        testPayment.setStatus(PaymentStatus.REJECTED.getValue());
        
        assertEquals(PaymentStatus.REJECTED.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testPaymentWithVoucherSetNullStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            testPayment.setStatus(null);
        });
        
        paymentData.clear();
    }

    @Test
    void testPaymentWithVoucherSetInvalidStatus() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            testPayment.setStatus("KodeAbal");
        });

        paymentData.clear();
    }

    @Test
    void testCashOnDeliverySetSuccessStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");

        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);
        testPayment.setStatus(PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), testPayment.getStatus());
        paymentData.clear();
    }

    @Test
    void testCashOnDeliverySetRejectedStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");
        
        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);
        testPayment.setStatus(PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), testPayment.getStatus());
        paymentData.clear();
    }
    
    @Test
    void testCashOnDeliverySetNullStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");
        
        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            testPayment.setStatus(null);
        });

        paymentData.clear();
    }

    @Test
    void testCashOnDeliverySetInvalidStatus() {
        paymentData.put("address", "Universitas Indonesia");
        paymentData.put("deliveryFee", "2000");
        
        Payment testPayment = new Payment("e6e60d39-41fb-4ff0-8631-3491e483c180", 
            "", order, paymentData);

        assertThrows(IllegalArgumentException.class, () -> {
            testPayment.setStatus("KodeAbal");
        });
        
        paymentData.clear();
    }

}
