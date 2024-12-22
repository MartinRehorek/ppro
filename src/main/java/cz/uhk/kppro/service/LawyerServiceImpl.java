package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Lawyer;
import cz.uhk.kppro.repository.CaseRepository;
import cz.uhk.kppro.repository.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LawyerServiceImpl implements LawyerService {

    private LawyerRepository lawyerRepository;

    @Autowired
    public LawyerServiceImpl(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public List<Lawyer> getAllLawyers() {
        return lawyerRepository.findAll();
    }

    @Override
    public Lawyer getLawyerById(long id) {
        return lawyerRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLawyerById(long id) {
        Optional<Lawyer> lawyer = lawyerRepository.findById(id);
        if (lawyer.isPresent()) {
            lawyerRepository.delete(lawyer.get());
        }
    }

    @Override
    public void saveLawyer(Lawyer lawyer) {
        lawyerRepository.save(lawyer);
    }
}
