package cz.uhk.kppro.controller;

import cz.uhk.kppro.model.Case;
import cz.uhk.kppro.model.CaseDocument;
import cz.uhk.kppro.service.CaseDocumentService;
import cz.uhk.kppro.service.CaseService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/cases")
public class caseController {

    private CaseService caseService;
    private LawyerService lawyerService;
    private CaseDocumentService caseDocumentService;

    @Autowired
    public caseController(CaseService caseService, LawyerService lawyerService, CaseDocumentService caseDocumentService) {
        this.caseService = caseService;
        this.lawyerService = lawyerService;
        this.caseDocumentService = caseDocumentService;
    }


    @GetMapping({"/", ""})
    public String listAllCases(Model model) {
        model.addAttribute("cases", caseService.getAllCases());
        return "case_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable long id, Model model) {
        Case caseDetail = caseService.getCaseById(id);
        if (caseDetail != null) {
            model.addAttribute("case", caseDetail);
            return "case_detail";
        }
        return "redirect:/caseDocuments/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id){
        Case caseObj = caseService.getCaseById(id);
        if (caseObj != null) {
            model.addAttribute("case", caseObj);
            model.addAttribute("lawyers", lawyerService.getAllLawyers());
            model.addAttribute("edit", true);
            return "case_edit";
        }
        return "redirect:/cases/";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("case", new Case());
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
    public String save(@Valid Case caseObj, 
                      BindingResult bindingResult,
                      @RequestParam(required = false) MultipartFile document,
                      @RequestParam(required = false) String documentTitle,
                      @RequestParam(required = false) String documentDescription,
                      Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("lawyers", lawyerService.getAllLawyers());
            model.addAttribute("edit", caseObj.getId() != 0);
            return "case_edit";
        }

        // First save the case
        Case savedCase = caseService.saveCase(caseObj);

        // If there's a document uploaded, create and associate it with the case
        if (document != null && !document.isEmpty() && documentTitle != null && !documentTitle.isEmpty()) {
            CaseDocument caseDocument = new CaseDocument();
            caseDocument.setTitle(documentTitle);
            caseDocument.setDescription(documentDescription);
            caseDocument.setCaseEntity(savedCase);
            caseDocument.setUploadDate(LocalDateTime.now());
            
            // TODO: Handle file upload and set filepath
            caseDocument.setFilePath("temp/path/" + document.getOriginalFilename());
            
            // TODO: Set uploaded by user (get from security context)
            // caseDocument.setUploadedBy(currentUser);
            
            caseDocumentService.saveCaseDocument(caseDocument);
        }

        return "redirect:/cases/";
    }

}
