package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Case;
import cz.uhk.kppro.repository.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseServiceImpl implements CaseService {

    private final CaseRepository caseRepository;

    @Autowired
    public CaseServiceImpl(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    @Override
    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    @Override
    public Case getCaseById(long id) {
        return caseRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCaseById(long id) {
        Optional<Case> Case = caseRepository.findById(id);
        if (Case.isPresent()) {
            caseRepository.delete(Case.get());
        }
    }

    @Override
    public void saveCase(Case Case) {
        caseRepository.save(Case);
    }
}
