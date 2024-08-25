package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Booking;
import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.BookingAvailability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByStudent(Student student);

    List<Booking> findAllByRoom(Room room);

    List<Booking> findAllByAvailability(BookingAvailability availability);

    List<Booking> findAllByBookingDateBetween(LocalDate startDate, LocalDate endDate);

    List<Booking> findAllByBookingDate(LocalDate bookingDate);

    List<Booking> findAllByRoomDorm(Dorm dorm);
}
