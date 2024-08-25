package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Booking;
import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.BookingAvailability;

import java.time.LocalDate;
import java.util.List;

public interface BookingService {

    Booking findBookingById(Long bookingId);

    List<Booking> findAllBookings();

    List<Booking> findBookingsByStudent(Student student);

    List<Booking> getBookingsByDorm(Dorm dorm);

    List<Booking> findBookingsByRoom(Room room);

    List<Booking> findBookingsByAvailability(BookingAvailability availability);

    List<Booking> findBookingsByDateRange(LocalDate startDate, LocalDate endDate);

    List<Booking> findAllByBookingDate(LocalDate bookingDate);

    Booking createBooking(Student student, Room room, Dorm dorm, LocalDate bookingDate, BookingAvailability availability);

    Booking updateBooking(Long bookingId, Student student, Room room, Dorm dorm, LocalDate bookingDate, BookingAvailability availability);

    Booking cancelBooking(Long bookingId);

    void deleteBooking(Long bookingId);
}