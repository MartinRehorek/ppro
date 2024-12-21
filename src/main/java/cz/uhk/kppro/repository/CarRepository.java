package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
//    List<Car> getAllByColorContainsIgnoreCase(String color);
//    Car getByColor(String  color);
//    List<Car> getAllByBrandContainsIgnoreCase(String brand);
//    List<Car> getCarsByNumberOfSeatsBetweenAndColorContains..
}
