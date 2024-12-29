package cz.uhk.kppro.service;

import cz.uhk.kppro.model.CaseDocument;
import cz.uhk.kppro.repository.CaseDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseDocumentServiceImpl implements  CaseDocumentService {
    private CaseDocumentRepository caseDocumentRepository;

    @Autowired
    public CaseDocumentServiceImpl(CaseDocumentRepository caseDocumentRepository) {
        this.caseDocumentRepository = caseDocumentRepository;
    }

    @Override
    public List<CaseDocument> getAllCaseDocuments() { return caseDocumentRepository.findAll(); }
    @Override
    public CaseDocument getCaseDocumentById(long id) { return caseDocumentRepository.findById(id).orElse(null); }
    @Override
    public void deleteCaseDocumentById(long id) { caseDocumentRepository.deleteById(id); }
    @Override
    public void saveCaseDocument(CaseDocument caseDocument) { caseDocumentRepository.save(caseDocument); }

}
