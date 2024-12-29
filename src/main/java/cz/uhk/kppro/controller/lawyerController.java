package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Lawyer;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.LawyerService;
import cz.uhk.kppro.service.UserService;
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
@RequestMapping( "/lawyers")
public class lawyerController {

    private final UserService userService;
    private LawyerService lawyerService;

    @Autowired
    public lawyerController(LawyerService lawyerService, UserService userService) {
        this.lawyerService = lawyerService;
        this.userService = userService;
    }

    @GetMapping({"/", ""})
    public String listAllLawyers(Model model){
        model.addAttribute("lawyers", lawyerService.getAllLawyers());
        return "lawyer_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Lawyer lawyer = lawyerService.getLawyerById(id);

        if (lawyer != null) {
            model.addAttribute("lawyer", lawyer);
            return "lawyer_detail";
        }
        return "redirect:/lawyers/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        Lawyer lawyer = lawyerService.getLawyerById(id);
        if (lawyer != null) {
            model.addAttribute("lawyer", lawyer);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("edit", true);
            return "lawyer_edit";
        }
        return "redirect:/lawyers/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("lawyer", new Lawyer());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("edit", false);
        return "lawyer_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        lawyerService.deleteLawyerById(id);
        return "redirect:/lawyers/";
    }
    @PostMapping("/save")
    public String save(@Valid Lawyer lawyer, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", lawyer.getId() != 0);
            return "lawyer_edit";
        }
        lawyerService.saveLawyer(lawyer);
        return "redirect:/lawyers/";
    }

}
