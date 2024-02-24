package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CarServiceTest {
    
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

    @Test
    public void testCreateCar() {
        Car car = new Car();
        car.setCarId("thisisnewcarid");
        car.setCarColor("Blue");
        car.setCarName("McLaren");
        car.setCarQuantity(1);

        when(carRepository.create(car)).thenReturn(car);

        Car createdCar = carService.create(car);
        assertEquals(car, createdCar);

        verify(carRepository, times(1)).create(car);
    }

    @Test
    public void testUpdateCar() {
        Car car = new Car();
        car.setCarId("thisisnewcarid");
        car.setCarColor("Blue");
        car.setCarName("McLaren");
        car.setCarQuantity(1);

        Car updatedCar = new Car();
        updatedCar.setCarId("thisisnewcarid");
        updatedCar.setCarColor("Red");
        updatedCar.setCarName("McLaren");
        updatedCar.setCarQuantity(2);

        when(carRepository.findById("thisisnewcarid")).thenReturn(car);
        when(carRepository.update(car.getCarId(), updatedCar)).thenReturn(updatedCar);

        carService.update(car.getCarId(), updatedCar);
        assertEquals("Red", updatedCar.getCarColor());
        assertEquals("McLaren", updatedCar.getCarName());
        assertEquals(2, updatedCar.getCarQuantity());

        verify(carRepository, times(1)).update(car.getCarId(), updatedCar);
    }

    @Test
    public void testFindAllCars() {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car());
        carList.add(new Car());

        Iterator<Car> iterator = carList.iterator();

        when(carRepository.findAll()).thenReturn(iterator);

        List<Car> retrievedCars = carService.findAll();
        assertEquals(carList.size(), retrievedCars.size());

        verify(carRepository, times(1)).findAll();
    }

    @Test
    public void testFindCarById() {
        String carId = "123";
        Car car = new Car();

        when(carRepository.findById(carId)).thenReturn(car);

        Car foundCar = carService.findById(carId);

        assertEquals(car, foundCar);
        verify(carRepository, times(1)).findById(carId);
    }

    @Test
    public void testDeleteCar() {
        Car car1 = new Car();
        car1.setCarId("thisisnewcarid");
        car1.setCarColor("Blue");
        car1.setCarName("McLaren");
        car1.setCarQuantity(1);

        Car car2 = new Car();
        car2.setCarId("thisisnewestcarid");
        car2.setCarColor("Red");
        car2.setCarName("Mazda");
        car2.setCarQuantity(1);

        carService.create(car1);
        carService.create(car2);

        carService.deleteCarById(car1.getCarId());
        assertEquals(null, carService.findById(car1.getCarId()));
        verify(carRepository, times(1)).delete(car1.getCarId());
    }
}

