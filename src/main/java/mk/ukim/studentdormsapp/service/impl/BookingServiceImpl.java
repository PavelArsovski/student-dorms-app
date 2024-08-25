package mk.ukim.studentdormsapp.service.impl;

import mk.ukim.studentdormsapp.model.Booking;
import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.BookingAvailability;
import mk.ukim.studentdormsapp.repository.BookingRepository;
import mk.ukim.studentdormsapp.service.BookingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking findBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public List<Booking> findBookingsByStudent(Student student) {
        return bookingRepository.findAllByStudent(student);
    }

    @Override
    public List<Booking> getBookingsByDorm(Dorm dorm) {
        return bookingRepository.findAllByRoomDorm(dorm);
    }

    @Override
    public List<Booking> findBookingsByRoom(Room room) {
        return bookingRepository.findAllByRoom(room);
    }

    @Override
    public List<Booking> findBookingsByAvailability(BookingAvailability availability) {
        return bookingRepository.findAllByAvailability(availability);
    }

    @Override
    public List<Booking> findBookingsByDateRange(LocalDate startDate, LocalDate endDate) {
        return bookingRepository.findAllByBookingDateBetween(startDate, endDate);
    }

    @Override
    public List<Booking> findAllByBookingDate(LocalDate bookingDate) {
        return bookingRepository.findAllByBookingDate(bookingDate);
    }

    @Override
    public Booking createBooking(Student student, Room room, Dorm dorm, LocalDate bookingDate, BookingAvailability availability) {
        Booking booking = new Booking();
        booking.setStudent(student);
        booking.setRoom(room);
        booking.setDorm(dorm);
        booking.setBookingDate(bookingDate);
        booking.setAvailability(availability);
        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Long bookingId, Student student, Room room, Dorm dorm, LocalDate bookingDate, BookingAvailability availability) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            booking.setStudent(student);
            booking.setRoom(room);
            booking.setDorm(dorm);
            booking.setBookingDate(bookingDate);
            booking.setAvailability(availability);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public Booking cancelBooking(Long bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            booking.setAvailability(BookingAvailability.UNAVAILABLE);
            return bookingRepository.save(booking);
        }
        return null;
    }

    @Override
    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}