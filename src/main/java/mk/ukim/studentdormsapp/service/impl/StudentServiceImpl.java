package mk.ukim.studentdormsapp.service.impl;


import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.Faculties;
import mk.ukim.studentdormsapp.model.enumeration.StudyYear;
import mk.ukim.studentdormsapp.model.exception.InvalidStudentException;
import mk.ukim.studentdormsapp.repository.StudentRepository;
import mk.ukim.studentdormsapp.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new InvalidStudentException("Student with ID " + studentId + " not found."));
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findStudentsByFaculties(Faculties faculties) {
        return studentRepository.findByFaculties(faculties);
    }

    @Override
    public List<Student> findStudentsByStudyYear(StudyYear studyYear) {
        return studentRepository.findByStudyYear(studyYear);
    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidStudentException("Student with email " + email + " not found."));
    }

    @Override
    public Student createStudent(String name, String surname, String email, String phone, String address, String city, Faculties faculties, StudyYear studyYear) {
        Student student = new Student(null, name, surname, email, phone, address, city, faculties, studyYear);
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long studentId, String name, String surname, String email, String phone, String address, String city, Faculties faculties, StudyYear studyYear) {
        Student student = findStudentById(studentId);
        student.setName(name);
        student.setSurname(surname);
        student.setEmail(email);
        student.setPhone(phone);
        student.setAddress(address);
        student.setCity(city);
        student.setFaculties(faculties);
        student.setStudyYear(studyYear);
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = findStudentById(studentId);
        studentRepository.delete(student);
    }
}