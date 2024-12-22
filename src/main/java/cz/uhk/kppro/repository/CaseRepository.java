package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaseRepository extends JpaRepository<Case, Long> {
}
