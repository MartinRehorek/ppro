package cz.uhk.kppro.service;

import cz.uhk.kppro.model.CaseDocument;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseDocumentService {
    List<CaseDocument> getAllCaseDocuments();
    CaseDocument getCaseDocumentById(long id);
    void deleteCaseDocumentById(long id);
    void saveCaseDocument(CaseDocument CaseDocument);
}
