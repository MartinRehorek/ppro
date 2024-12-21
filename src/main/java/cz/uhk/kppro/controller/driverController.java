package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Driver;
import cz.uhk.kppro.service.CarService;
import cz.uhk.kppro.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/drivers")
public class driverController {

    private DriverService driverService;
    private CarService carService;

    @Autowired
    public driverController(DriverService driverService, CarService carService) {
        this.driverService = driverService;
        this.carService = carService;
    }


    @GetMapping({"/", ""})
    public String listAllDrivers(Model model) {
//        Driver driver = new Driver("modra", 4,  "2333");
//        drivers.add(driver);
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "driver_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            model.addAttribute("driver", driver);
            return "driver_detail";
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        Driver driver = driverService.getDriverById(id);
        if (driver != null) {
            model.addAttribute("driver", driver);
            model.addAttribute("cars", carService.getAllCars());
            model.addAttribute("edit", true);
            return "driver_edit";
        }
        return "redirect:/drivers/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("driver", new Driver());
        model.addAttribute("cars", carService.getAllCars());
        model.addAttribute("edit", false);
        return "driver_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        driverService.deleteDriverById(id);
        return "redirect:/drivers/";
    }
    @PostMapping("/save")
    public String save(@Valid Driver driver, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", driver.getId() != 0);
            return "driver_edit";
        }
        driverService.saveDriver(driver);
        return "redirect:/drivers/";
    }

}
