package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.Faculties;
import mk.ukim.studentdormsapp.model.enumeration.StudyYear;
import mk.ukim.studentdormsapp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String getAllStudents(Model model) {
        List<Student> students = studentService.findAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("bodyContent", "student/students");
        return "master-template";
    }

    @GetMapping("/{studentId}")
    public String getStudentById(@PathVariable Long studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("student", student);
        model.addAttribute("bodyContent", "student/studentDetails");
        return "master-template";
    }

    @GetMapping("/create")
    public String showCreateStudentForm(Model model) {
        model.addAttribute("faculties", Faculties.values());
        model.addAttribute("studyYears", StudyYear.values());
        model.addAttribute("bodyContent", "student/add-edit-form");
        return "master-template";
    }

    @PostMapping("/create")
    public String createStudent(@RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String email,
                                @RequestParam String phone,
                                @RequestParam String address,
                                @RequestParam String city,
                                @RequestParam Faculties faculties,
                                @RequestParam StudyYear studyYear) {
        studentService.createStudent(name, surname, email, phone, address, city, faculties, studyYear);
        return "redirect:/students";
    }

    @GetMapping("/edit/{studentId}")
    public String showEditStudentForm(@PathVariable Long studentId, Model model) {
        Student student = studentService.findStudentById(studentId);
        model.addAttribute("faculties", Faculties.values());
        model.addAttribute("studyYears", StudyYear.values());
        model.addAttribute("student", student);
        model.addAttribute("bodyContent", "student/add-edit-form");
        return "master-template";
    }

    @PostMapping("/edit/{studentId}")
    public String editStudent(@PathVariable Long studentId,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String email,
                              @RequestParam String phone,
                              @RequestParam String address,
                              @RequestParam String city,
                              @RequestParam Faculties faculties,
                              @RequestParam StudyYear studyYear) {
        studentService.updateStudent(studentId, name, surname, email, phone, address, city, faculties, studyYear);
        return "redirect:/students";
    }

    @PostMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return "redirect:/students";
    }
}