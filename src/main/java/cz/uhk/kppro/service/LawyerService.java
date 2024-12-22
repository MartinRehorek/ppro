package cz.uhk.kppro.service;

import cz.uhk.kppro.model.Lawyer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LawyerService {
    List<Lawyer> getAllLawyers();
    Lawyer getLawyerById(long id);
    void deleteLawyerById(long id);
    void saveLawyer(Lawyer lawyer);
    
}
