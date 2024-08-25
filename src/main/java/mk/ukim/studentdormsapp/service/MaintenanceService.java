package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Maintenance;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.MaintenanceStatus;

import java.time.LocalDate;
import java.util.List;

public interface MaintenanceService {

    Maintenance findMaintenanceById(Long maintenanceId);

    List<Maintenance> findAllMaintenanceRequests();

    List<Maintenance> findMaintenanceRequestsByRoom(Room room);

    List<Maintenance> findMaintenanceRequestsByStatus(MaintenanceStatus status);

    List<Maintenance> findMaintenanceRequestsByDateRange(LocalDate startDate, LocalDate endDate);

    Maintenance createMaintenance(Room room, LocalDate date, MaintenanceStatus status, String description);

    Maintenance updateMaintenance(Long maintenanceId, Room room, LocalDate date, MaintenanceStatus status, String description);

    void deleteMaintenance(Long maintenanceId);

    Maintenance changeMaintenanceStatus(Long maintenanceId, MaintenanceStatus status);
}