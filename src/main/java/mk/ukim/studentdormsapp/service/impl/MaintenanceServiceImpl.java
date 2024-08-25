package mk.ukim.studentdormsapp.service.impl;

import mk.ukim.studentdormsapp.model.Maintenance;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.MaintenanceStatus;
import mk.ukim.studentdormsapp.model.exception.InvalidMaintenanceException;
import mk.ukim.studentdormsapp.repository.MaintenanceRepository;
import mk.ukim.studentdormsapp.service.MaintenanceService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImpl(MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance findMaintenanceById(Long maintenanceId) {
        return maintenanceRepository.findById(maintenanceId)
                .orElseThrow(() -> new InvalidMaintenanceException("Maintenance request with ID " + maintenanceId + " not found"));
    }

    @Override
    public List<Maintenance> findAllMaintenanceRequests() {
        return maintenanceRepository.findAll();
    }

    @Override
    public List<Maintenance> findMaintenanceRequestsByRoom(Room room) {
        return maintenanceRepository.findByRoom(room);
    }

    @Override
    public List<Maintenance> findMaintenanceRequestsByStatus(MaintenanceStatus status) {
        return maintenanceRepository.findByStatus(status);
    }

    @Override
    public List<Maintenance> findMaintenanceRequestsByDateRange(LocalDate startDate, LocalDate endDate) {
        return maintenanceRepository.findByDateBetween(startDate, endDate);
    }

    @Override
    public Maintenance createMaintenance(Room room, LocalDate date, MaintenanceStatus status, String description) {
        Maintenance maintenance = new Maintenance();
        maintenance.setRoom(room);
        maintenance.setDate(date);
        maintenance.setStatus(status);
        maintenance.setDescription(description);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public Maintenance updateMaintenance(Long maintenanceId, Room room, LocalDate date, MaintenanceStatus status, String description) {
        Maintenance maintenance = findMaintenanceById(maintenanceId);
        maintenance.setRoom(room);
        maintenance.setDate(date);
        maintenance.setStatus(status);
        maintenance.setDescription(description);
        return maintenanceRepository.save(maintenance);
    }

    @Override
    public void deleteMaintenance(Long maintenanceId) {
        Maintenance maintenance = findMaintenanceById(maintenanceId);
        maintenanceRepository.delete(maintenance);
    }

    @Override
    public Maintenance changeMaintenanceStatus(Long maintenanceId, MaintenanceStatus status) {
        Maintenance maintenance = findMaintenanceById(maintenanceId);
        maintenance.setStatus(status);
        return maintenanceRepository.save(maintenance);
    }
}