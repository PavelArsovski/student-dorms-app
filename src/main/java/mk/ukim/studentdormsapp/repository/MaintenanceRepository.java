package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Maintenance;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.MaintenanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Long> {

    List<Maintenance> findByRoom(Room room);

    List<Maintenance> findByStatus(MaintenanceStatus status);

    List<Maintenance> findByDateBetween(LocalDate startDate, LocalDate endDate);
}