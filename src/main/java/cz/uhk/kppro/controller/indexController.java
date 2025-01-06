package cz.uhk.kppro.controller;

import jakarta.persistence.OneToMany;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.UserService;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Controller
public class indexController {

    private final UserService userService;

    public indexController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user;
        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            user = userService.findByUsername(username);
        } else {
            // Handle the case where the user is not authenticated
            user = null;
        }

        model.addAttribute("user", user);
        return "index";
    }

    // @GetMapping("/admin")
    // @ResponseBody
    // public String admin() {
    //     return "<h1 style=\"color: green\">Admin section</h1>";
    // }
    @GetMapping("/403")
    @ResponseBody
    public String forbidden() {
        return "<h1>Forbidden</h1>";
    }
}
