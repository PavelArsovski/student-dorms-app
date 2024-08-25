package mk.ukim.studentdormsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.studentdormsapp.model.enumeration.Faculties;
import mk.ukim.studentdormsapp.model.enumeration.StudyYear;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String address;

    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Faculties faculties;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StudyYear studyYear;
}