package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Case;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CaseService {
    List<Case> getAllCases();
    Case getCaseById(long id);
    void deleteCaseById(long id);
    void saveCase(Case Case);
}
