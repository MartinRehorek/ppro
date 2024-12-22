package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {

}
