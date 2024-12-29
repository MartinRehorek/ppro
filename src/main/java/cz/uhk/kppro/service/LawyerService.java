package cz.uhk.kppro.service;

import java.util.List;
import cz.uhk.kppro.model.Lawyer;

public interface LawyerService {
    List<Lawyer> getAllLawyers();
    Lawyer getLawyerById(long id);
    Lawyer getLawyerByEmail(String email);
    void deleteLawyerById(long id);
    void saveLawyer(Lawyer lawyer);
}
