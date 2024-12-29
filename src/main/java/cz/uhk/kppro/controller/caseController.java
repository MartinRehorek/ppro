package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Case;
import cz.uhk.kppro.model.User;
import cz.uhk.kppro.service.CaseService;
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
@RequestMapping( "/cases")
public class caseController {

    private CaseService caseService;
    private final LawyerService lawyerService;
    private final UserService userService;


    @Autowired
    public caseController(CaseService caseService, UserService userService, LawyerService lawyerService) {
        this.caseService = caseService;
        this.userService = userService;
        this.lawyerService = lawyerService;
    }

    @GetMapping({"/", ""})
    public String listAllCases(Model model){
        model.addAttribute("cases", caseService.getAllCases());
        return "case_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Case caseObject = caseService.getCaseById(id);

        if (caseObject != null) {
            model.addAttribute("case", caseObject);
            return "case_detail";
        }
        return "redirect:/cases/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        Case caseObject = caseService.getCaseById(id);
        if (caseObject != null) {
            model.addAttribute("case", caseObject);
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("edit", true);
            return "case_edit";
        }
        return "redirect:/cases/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("case", new Case());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("lawyers", lawyerService.getAllLawyers());
        model.addAttribute("edit", false);
        return "case_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        caseService.deleteCaseById(id);
        return "redirect:/cases/";
    }
    @PostMapping("/save")
    public String save(@Valid Case caseObject, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", caseObject.getId() != 0);
            return "case_edit";
        }
        caseService.saveCase(caseObject);
        return "redirect:/cases/";
    }

}
