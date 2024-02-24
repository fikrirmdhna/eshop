package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class CarRepository {
    private List<Car> carData = new ArrayList<>();

    public Car create(Car car) {
        carData.add(car);
        return car; 
    }

    public Iterator<Car> findAll() {
        return carData.iterator();
    }

    public Car findById(String id) {
        Car thisCar = null;
        for (Car car : carData) {
            if (car.getCarId().equals(id)) {
                thisCar = car;
            }
        }
        return thisCar;
    }

    public Car update(String id,  Car updatedCar) {
        Car selectedCar = findById(id);
        selectedCar.setCarName(updatedCar.getCarName());
        selectedCar.setCarColor(updatedCar.getCarColor());
        selectedCar.setCarQuantity(updatedCar.getCarQuantity());
        return selectedCar;
    }

    public void delete(String id) { carData.removeIf(car -> car.getCarId().equals(id)); }
    
}
