package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Case;

import java.util.List;

public interface CaseService {
    List<Case> getAllCases();
    Case getCaseById(long id);
    void deleteCaseById(long id);
    void saveCase(Case caseObject);
}
