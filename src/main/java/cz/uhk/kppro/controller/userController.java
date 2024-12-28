package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class userController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public userController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/", ""})
    public String listAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "user_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            return "user_detail";
        }
        return "redirect:/users/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {
        User user = userService.findById(id);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("edit", true);
            return "user_edit";
        }
        return "redirect:/users/";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("edit", false);
        return "user_edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        userService.deleteById(id);
        return "redirect:/users/";
    }

    @PostMapping("/save")
    public String save(@Valid User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.toString()));
            model.addAttribute("edit", user.getId() != null);
            return "user_edit";
        }
        
        // Encode password for new users
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("ROLE_USER");  // Set default role for new users
        }
        
        userService.save(user);
        return "redirect:/users/";
    }
}