package cz.uhk.kppro.controller;

import jakarta.persistence.OneToMany;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class indexController {

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "<h1 style=\"color: green\">Admin section</h1>";
    }
    @GetMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "<h1>Forbidden</h1>";
    }
}
