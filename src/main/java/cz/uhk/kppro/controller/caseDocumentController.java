package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.CaseDocument;
import cz.uhk.kppro.service.CaseDocumentService;
import cz.uhk.kppro.service.CaseService;
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
@RequestMapping( "/caseDocuments")
public class caseDocumentController {

    private final CaseService caseService;
    private CaseDocumentService caseDocumentService;

    @Autowired
    public caseDocumentController(CaseDocumentService caseDocumentService, CaseService caseService) {
        this.caseDocumentService = caseDocumentService;
        this.caseService = caseService;
    }

    @GetMapping({"/", ""})
    public String listAllCaseDocuments(Model model){
        model.addAttribute("caseDocuments", caseDocumentService.getAllCaseDocuments());
        return "case_document_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        CaseDocument caseDocumentObject = caseDocumentService.getCaseDocumentById(id);

        if (caseDocumentObject != null) {
            model.addAttribute("caseDocument", caseDocumentObject);
            return "case_document_detail";
        }
        return "redirect:/caseDocuments/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        CaseDocument caseDocumentObject = caseDocumentService.getCaseDocumentById(id);
        if (caseDocumentObject != null) {
            model.addAttribute("caseDocument", caseDocumentObject);
            model.addAttribute("cases", caseService.getAllCases());
            model.addAttribute("edit", true);
            return "case_document_edit";
        }
        return "redirect:/caseDocuments/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("caseDocument", new CaseDocument());
        model.addAttribute("cases", caseService.getAllCases());
        model.addAttribute("edit", false);
        return "case_document_edit";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id){
        caseDocumentService.deleteCaseDocumentById(id);
        return "redirect:/caseDocuments/";
    }
    @PostMapping("/save")
    public String save(@Valid CaseDocument caseDocumentObject, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("edit", caseDocumentObject.getId() != 0);
            model.addAttribute("cases", caseService.getAllCases());
            return "case_document_edit";
        }
        caseDocumentService.saveCaseDocument(caseDocumentObject);
        return "redirect:/caseDocuments/";
    }

}
