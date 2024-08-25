package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.Faculties;
import mk.ukim.studentdormsapp.model.enumeration.StudyYear;

import java.util.List;

public interface StudentService {

    Student findStudentById(Long studentId);

    List<Student> findAllStudents();

    List<Student> findStudentsByFaculties(Faculties faculties);

    List<Student> findStudentsByStudyYear(StudyYear studyYear);

    Student findStudentByEmail(String email);

    Student createStudent(String name, String surname, String email, String phone, String address, String city, Faculties faculties, StudyYear studyYear);

    Student updateStudent(Long studentId, String name, String surname, String email, String phone, String address, String city, Faculties faculties, StudyYear studyYear);

    void deleteStudent(Long studentId);
}
