package cz.uhk.kppro.service;

import cz.uhk.kppro.model.CaseDocument;

import java.util.List;

public interface CaseDocumentService {
    List<CaseDocument> getAllCaseDocuments();
    CaseDocument getCaseDocumentById(long id);
    void deleteCaseDocumentById(long id);
    void saveCaseDocument(CaseDocument caseDocument);
}
