package cz.uhk.kppro.repository;

import cz.uhk.kppro.model.Lawyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LawyerRepository extends JpaRepository<Lawyer, Long> {
    @Query("SELECT l FROM Lawyer l WHERE l.user.email = :email")
    Lawyer getLawyerByEmail(@Param("email") String email);
}
