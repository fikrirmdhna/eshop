package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class CarTest {
    Car car;
    @BeforeEach
    void setUp() {
        this.car = new Car();
        this.car.setCarId("thisisnewcarid");
        this.car.setCarColor("Blue");
        this.car.setCarName("McLaren");
        this.car.setCarQuantity(1);
    }
    @Test
    void testGetCarId() {
        assertEquals("thisisnewcarid", this.car.getCarId());
    }
    @Test
    void testGetCarColor() {
        assertEquals("Blue", this.car.getCarColor());
    }
    @Test
    void testGetCarName() {
        assertEquals("McLaren", this.car.getCarName());
    }
    @Test
    void testGetCarQuantity() {
        assertEquals(1, this.car.getCarQuantity());
    }
}