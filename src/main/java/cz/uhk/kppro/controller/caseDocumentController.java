package cz.uhk.kppro.controller;


import cz.uhk.kppro.model.CaseDocument;
import cz.uhk.kppro.service.CaseDocumentService;
import cz.uhk.kppro.service.LawyerService;
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
@RequestMapping("/caseDocuments")
public class caseDocumentController {

    private CaseDocumentService caseDocumentService;
    private LawyerService lawyerService;

    @Autowired
    public caseDocumentController(CaseDocumentService caseDocumentService, LawyerService lawyerService) {
        this.caseDocumentService = caseDocumentService;
        this.lawyerService = lawyerService;
    }


    @GetMapping({"/", ""})
    public String listAllCaseDocuments(Model model) {
//        CaseDocument caseDocument = new CaseDocument("modra", 4,  "2333");
//        caseDocuments.add(caseDocument);
        model.addAttribute("caseDocuments", caseDocumentService.getAllCaseDocuments());
        return "caseDocument_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        CaseDocument caseDocument = caseDocumentService.getCaseDocumentById(id);
        if (caseDocument != null) {
            model.addAttribute("caseDocument", caseDocument);
                return "caseDocument_detail";
        }
        return "redirect:/caseDocuments/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        CaseDocument caseDocument = caseDocumentService.getCaseDocumentById(id);
        if (caseDocument != null) {
            model.addAttribute("caseDocument", caseDocument);
                model.addAttribute("lawyers", lawyerService.getAllLawyers());
                model.addAttribute("edit", true);
                return "caseDocument_edit";
        }
        return "redirect:/caseDocuments/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("caseDocument", new CaseDocument());
        model.addAttribute("lawyers", lawyerService.getAllLawyers());
        model.addAttribute("edit", false);
        return "caseDocument_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        caseDocumentService.deleteCaseDocumentById(id);
        return "redirect:/caseDocuments/";
    }
    @PostMapping("/save")
    public String save(@Valid CaseDocument caseDocument, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", caseDocument.getId() != 0);
                return "caseDocument_edit";
        }
        caseDocumentService.saveCaseDocument(caseDocument);
        return "redirect:/caseDocuments/";
    }

}
