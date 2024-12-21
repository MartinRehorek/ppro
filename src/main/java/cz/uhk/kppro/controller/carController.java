package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Car;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping("/cars")
public class carController {

    private ArrayList<Car> cars = new ArrayList<>();

    @GetMapping({"/", ""})
    public String listAllCars(Model model) {
//        Car car = new Car("modra", 4,  "2333");
//        cars.add(car);
        model.addAttribute("cars", cars);
        return "car_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable int id, Model model) {
        Car car = cars.get(id);
        cars.set(id, car);
        model.addAttribute("car", car);
        return "car_detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        Car car = cars.get(id);
        car.setId(id);
        model.addAttribute("car", car);
        model.addAttribute("edit", true);
        return "car_edit";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        return "car_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        cars.remove(id);
        return "redirect:/cars/";
    }
    @PostMapping("/save")
    public String save(@Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", car.getId() == -1);
            return "car_edit";
        }
        if(car.getId() != -1){
            cars.remove(car.getId());
        }
        cars.add(car);
        return "redirect:/cars/";
    }

}
