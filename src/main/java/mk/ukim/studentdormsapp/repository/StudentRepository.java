package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.Faculties;
import mk.ukim.studentdormsapp.model.enumeration.StudyYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByFaculties(Faculties faculties);

    Optional<Student> findByEmail(String email);

    List<Student> findByStudyYear(StudyYear studyYear);
}