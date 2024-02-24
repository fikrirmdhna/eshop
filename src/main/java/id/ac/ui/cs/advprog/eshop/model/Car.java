package id.ac.ui.cs.advprog.eshop.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Car {
    private String carId;
    private String carName;
    private String carColor;
    private int carQuantity;

    public Car() {
        this.setCarId(UUID.randomUUID().toString());
    }

    public Car update(Car updatedCar) {
        this.setCarName(updatedCar.getCarName());
        this.setCarColor(updatedCar.getCarColor());
        this.setCarQuantity(updatedCar.getCarQuantity());

        return this;
    }
}
