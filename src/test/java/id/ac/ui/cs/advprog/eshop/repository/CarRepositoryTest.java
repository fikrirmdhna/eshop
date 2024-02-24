package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CarRepositoryTest {
    

    @InjectMocks
    CarRepository carRepository;
    @BeforeEach
    void setUp() {
    }
    @Test
    void testCreateAndFind() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("McLaren");
        car.setCarQuantity(100);
        carRepository.create(car);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedProduct = carIterator.next();
        assertEquals(car.getCarId(), savedProduct.getCarId());
        assertEquals(car.getCarName(), savedProduct.getCarName());
        assertEquals(car.getCarQuantity(), savedProduct.getCarQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Car> carIterator = carRepository.findAll();
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("McLaren");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        car2.setCarName("BMW");
        car2.setCarQuantity(50);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.findAll();
        assertTrue(carIterator.hasNext());
        Car savedProduct = carIterator.next();
        assertEquals(car1.getCarId(), savedProduct.getCarId());
        savedProduct = carIterator.next();
        assertEquals(car2.getCarId(), savedProduct.getCarId());
        assertFalse(carIterator.hasNext());
    }
    @Test
    void testUpdateCar() {
        Car car = new Car();
        car.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car.setCarName("McLaren");
        car.setCarQuantity(100);
        carRepository.create(car);

        Car newCar = new Car();
        newCar.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        newCar.setCarName("Mazda");
        newCar.setCarQuantity(50);

        // applying name and quantity changes from newCar to car
        carRepository.update(car.getCarId(), newCar); 

        assertEquals("Mazda", car.getCarName());
        assertEquals(50, car.getCarQuantity());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", car.getCarId());
    }
    @Test
    void testDeleteCar() {
        Car car1 = new Car();
        car1.setCarId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        car1.setCarName("McLaren");
        car1.setCarQuantity(100);
        carRepository.create(car1);

        Car car2 = new Car();
        car2.setCarId("ab558e9f-1c39-460e-8860-71af6af63bd9");
        car2.setCarName("Mazda");
        car2.setCarQuantity(50);
        carRepository.create(car2);
        
        carRepository.delete(car1.getCarId()); //delete car1
        Iterator<Car> carIterator = carRepository.findAll();
        
        assertTrue(carIterator.hasNext());
        Car savedProduct = carIterator.next(); //"Mazda"

        assertEquals(car2.getCarName(), savedProduct.getCarName());
        assertThrows(NoSuchElementException.class, () -> {
            carIterator.next();
        });
        assertFalse(carIterator.hasNext());
    }
}
