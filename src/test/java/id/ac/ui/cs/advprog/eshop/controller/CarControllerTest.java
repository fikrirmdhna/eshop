package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@WebMvcTest(controllers = CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void testCreateCarPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/car/createCar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("CreateCar"));
    }

    @Test
    public void testCreateCarPost() throws Exception {
        Car car = new Car();
        when(carService.create(any(Car.class))).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/car/createCar")
                .param("carName", "Test Car")
                .param("carDescription", "Test Description"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));

        verify(carService, times(1)).create(any(Car.class));
    }

    @Test
    public void testEditCarPage() throws Exception {
        String carId = "123";
        Car car = new Car();
        when(carService.findById(carId)).thenReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.get("/car/editCar/{carId}", carId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("EditCar"))
                .andExpect(MockMvcResultMatchers.model().attribute("car", car));
    }

    @Test
    public void testEditCarPost() throws Exception {
        String carId = "123";
        Car car = new Car();
        car.setCarId(carId);

        doNothing().when(carService).update(eq(carId), any());

        mockMvc.perform(MockMvcRequestBuilders.post("/car/editCar", carId)
                .param("carName", "Test Car")
                .param("carColor", "Test Color")
                .param("carId", carId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));

        verify(carService, times(1)).update(eq(carId), any());
    }

    @Test
    public void testCarListPage() throws Exception {
        when(carService.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(MockMvcRequestBuilders.get("/car/listCar"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("CarList"))
                .andExpect(MockMvcResultMatchers.model().attribute("cars", Collections.emptyList()));
    }

    @Test
    public void testDeleteCarPost() throws Exception {
        String carId = "123";

        mockMvc.perform(MockMvcRequestBuilders.post("/car/deleteCar")
                .param("carId", carId))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("listCar"));

        verify(carService, times(1)).deleteCarById(carId);
    }
}
