package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Lawyer;
import cz.uhk.kppro.repository.LawyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LawyerServiceImpl implements LawyerService {
    private LawyerRepository lawyerRepository;

    @Autowired
    public LawyerServiceImpl(LawyerRepository lawyerRepository) {
        this.lawyerRepository = lawyerRepository;
    }

    @Override
    public List<Lawyer> getAllLawyers() { return lawyerRepository.findAll(); }
    @Override
    public Lawyer getLawyerByEmail(String email) { return lawyerRepository.getLawyerByEmail(email); }
    @Override
    public Lawyer getLawyerById(long id) { return lawyerRepository.findById(id).orElse(null); }
    @Override
    public void deleteLawyerById(long id) { lawyerRepository.deleteById(id); }
    @Override
    public void saveLawyer(Lawyer lawyer) { lawyerRepository.save(lawyer); }

}
