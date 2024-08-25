package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.ResidentAdvisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResidentAdvisorRepository extends JpaRepository<ResidentAdvisor, Long> {

    List<ResidentAdvisor> findByAssignedDorm(Dorm dorm);

    List<ResidentAdvisor> findByName(String name);

    List<ResidentAdvisor> findBySurname(String surname);

    Optional<ResidentAdvisor> findByEmail(String email);
}