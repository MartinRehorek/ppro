package cz.uhk.kppro.service;

import cz.uhk.kppro.model.CaseDocument;
import cz.uhk.kppro.repository.CaseDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseDocumentServiceImpl implements CaseDocumentService {

    private CaseDocumentRepository caseRepository;

    @Autowired
    public CaseDocumentServiceImpl(CaseDocumentRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<CaseDocument> getAllCaseDocuments() {
        return caseRepository.findAll();
    }

    @Override
    public CaseDocument getCaseDocumentById(long id) {
        return caseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCaseDocumentById(long id) {
        Optional<CaseDocument> caseDocument = caseRepository.findById(id);
        if (caseDocument.isPresent()) {
            caseRepository.delete(caseDocument.get());
        }
    }

    @Override
    public void saveCaseDocument(CaseDocument caseDocument) {
        caseRepository.save(caseDocument);
    }
}
