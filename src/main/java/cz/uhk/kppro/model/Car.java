package cz.uhk.kppro.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Car {
    private int id = -1;

    private String color;
    @Min(value = 1)
    @Max( value = 8)
    private int numberOfSeats;
    @NotBlank(message = "Mas to blbe")
    @Size(min = 3, max = 10)
    private String licensePlate;

    public Car(String color, int numberOfSeats, String licensePlate) {
        this.color = color;
        this.numberOfSeats = numberOfSeats;
        this.licensePlate = licensePlate;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
