package cz.uhk.kppro.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name= "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String color;
    @Min(value = 1)
    @Max( value = 8)
    private int numberOfSeats;
    @NotBlank(message = "Mas to blbe")
    @Size(min = 3, max = 10)
    private String licensePlate;

    // inverzni strana
//    @OneToOne(mappedBy = "car")
//    private Driver driver;

    @OneToMany(mappedBy = "car")
    private List<Driver> drivers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
