package mk.ukim.studentdormsapp.service.impl;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.ResidentAdvisor;
import mk.ukim.studentdormsapp.model.exception.InvalidResidentAdvisorException;
import mk.ukim.studentdormsapp.repository.ResidentAdvisorRepository;
import mk.ukim.studentdormsapp.service.ResidentAdvisorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResidentAdvisorServiceImpl implements ResidentAdvisorService {

    private final ResidentAdvisorRepository residentAdvisorRepository;

    public ResidentAdvisorServiceImpl(ResidentAdvisorRepository residentAdvisorRepository) {
        this.residentAdvisorRepository = residentAdvisorRepository;
    }

    @Override
    public ResidentAdvisor findResidentAdvisorById(Long residentAdvisorId) {
        return residentAdvisorRepository.findById(residentAdvisorId)
                .orElseThrow(() -> new InvalidResidentAdvisorException("ResidentAdvisor with id " + residentAdvisorId + " not found."));
    }

    @Override
    public List<ResidentAdvisor> findAllResidentAdvisors() {
        return residentAdvisorRepository.findAll();
    }

    @Override
    public List<ResidentAdvisor> findResidentAdvisorsByDorm(Dorm dorm) {
        return residentAdvisorRepository.findByAssignedDorm(dorm);
    }

    @Override
    public ResidentAdvisor findResidentAdvisorByEmail(String email) {
        return residentAdvisorRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidResidentAdvisorException("ResidentAdvisor with email " + email + " not found."));
    }

    @Override
    public List<ResidentAdvisor> findByName(String name) {
        List<ResidentAdvisor> advisors = residentAdvisorRepository.findByName(name);
        if (advisors.isEmpty()) {
            throw new InvalidResidentAdvisorException("No ResidentAdvisors found with name " + name);
        }
        return advisors;
    }

    @Override
    public List<ResidentAdvisor> findBySurname(String surname) {
        List<ResidentAdvisor> advisors = residentAdvisorRepository.findBySurname(surname);
        if (advisors.isEmpty()) {
            throw new InvalidResidentAdvisorException("No ResidentAdvisors found with surname " + surname);
        }
        return advisors;
    }


    @Override
    public ResidentAdvisor createResidentAdvisor(String name, String surname, String email, Dorm assignedDorm) {
        ResidentAdvisor residentAdvisor = new ResidentAdvisor(null, name, surname, email, assignedDorm);
        return residentAdvisorRepository.save(residentAdvisor);
    }

    @Override
    public ResidentAdvisor updateResidentAdvisor(Long residentAdvisorId, String name, String surname, String email, Dorm assignedDorm) {
        ResidentAdvisor residentAdvisor = findResidentAdvisorById(residentAdvisorId);
        residentAdvisor.setName(name);
        residentAdvisor.setSurname(surname);
        residentAdvisor.setEmail(email);
        residentAdvisor.setAssignedDorm(assignedDorm);
        return residentAdvisorRepository.save(residentAdvisor);
    }

    @Override
    public void deleteResidentAdvisor(Long residentAdvisorId) {
        ResidentAdvisor residentAdvisor = findResidentAdvisorById(residentAdvisorId);
        residentAdvisorRepository.delete(residentAdvisor);
    }

    @Override
    public ResidentAdvisor assignDormToResidentAdvisor(Long residentAdvisorId, Dorm dorm) {
        ResidentAdvisor residentAdvisor = findResidentAdvisorById(residentAdvisorId);
        residentAdvisor.setAssignedDorm(dorm);
        return residentAdvisorRepository.save(residentAdvisor);
    }

    @Override
    public ResidentAdvisor changeAssignedDorm(Long residentAdvisorId, Dorm newDorm) {
        return assignDormToResidentAdvisor(residentAdvisorId, newDorm);
    }
}