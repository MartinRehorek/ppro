package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Lawyer;
import cz.uhk.kppro.service.LawyerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lawyers")
public class lawyerController {

    private final LawyerService lawyerService;

    @Autowired
    public lawyerController(LawyerService lawyerService) {
        this.lawyerService = lawyerService;
    }

    @GetMapping({"/", ""})
    public String listAllLawyers(Model model) {
        model.addAttribute("lawyers", lawyerService.getAllLawyers());
        return "lawyer_list";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("lawyer", new Lawyer());
        model.addAttribute("edit", false);
        return "lawyer_edit";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Lawyer lawyer = lawyerService.getLawyerById(id);
        if (lawyer != null) {
            model.addAttribute("lawyer", lawyer);
            model.addAttribute("edit", true);
            return "lawyer_edit";
        }
        return "redirect:/lawyers/";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("lawyer") Lawyer lawyer, 
                      BindingResult bindingResult, 
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", lawyer.getId() != null);
            return "lawyer_edit";
        }
        lawyerService.saveLawyer(lawyer);
        return "redirect:/lawyers/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        lawyerService.deleteLawyerById(id);
        return "redirect:/lawyers/";
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
}
