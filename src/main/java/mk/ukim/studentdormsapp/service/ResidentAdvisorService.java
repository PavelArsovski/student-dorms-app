package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.ResidentAdvisor;

import java.util.List;

public interface ResidentAdvisorService {

    ResidentAdvisor findResidentAdvisorById(Long residentAdvisorId);

    List<ResidentAdvisor> findAllResidentAdvisors();

    List<ResidentAdvisor> findResidentAdvisorsByDorm(Dorm dorm);

    ResidentAdvisor findResidentAdvisorByEmail(String email);

    List<ResidentAdvisor> findByName(String name);

    List<ResidentAdvisor> findBySurname(String surname);

    ResidentAdvisor createResidentAdvisor(String name, String surname, String email, Dorm assignedDorm);

    ResidentAdvisor updateResidentAdvisor(Long residentAdvisorId, String name, String surname, String email, Dorm assignedDorm);

    void deleteResidentAdvisor(Long residentAdvisorId);

    ResidentAdvisor assignDormToResidentAdvisor(Long residentAdvisorId, Dorm dorm);

    ResidentAdvisor changeAssignedDorm(Long residentAdvisorId, Dorm newDorm);
}