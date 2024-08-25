package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Booking;
import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.Student;
import mk.ukim.studentdormsapp.model.enumeration.BookingAvailability;
import mk.ukim.studentdormsapp.service.BookingService;
import mk.ukim.studentdormsapp.service.DormService;
import mk.ukim.studentdormsapp.service.RoomService;
import mk.ukim.studentdormsapp.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final StudentService studentService;
    private final RoomService roomService;
    private final DormService dormService;

    public BookingController(BookingService bookingService, StudentService studentService, RoomService roomService, DormService dormService) {
        this.bookingService = bookingService;
        this.studentService = studentService;
        this.roomService = roomService;
        this.dormService = dormService;
    }

    @GetMapping
    public String getAllBookings(Model model) {
        List<Booking> bookings = bookingService.findAllBookings();
        model.addAttribute("bookings", bookings);
        model.addAttribute("bodyContent", "booking/bookings");
        return "master-template";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("rooms", roomService.findAllRooms());
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("booking", new Booking());
        model.addAttribute("bodyContent", "booking/bookingForm");
        return "master-template";
    }

    @PostMapping("/create")
    public String createBooking(
            @RequestParam Long studentId,
            @RequestParam Long roomId,
            @RequestParam Long dormId,
            @RequestParam String bookingDate,
            @RequestParam BookingAvailability availability) {

        Student student = studentService.findStudentById(studentId);
        Room room = roomService.findRoomById(roomId);
        Dorm dorm = dormService.findDormById(dormId);
        LocalDate date = LocalDate.parse(bookingDate);
        bookingService.createBooking(student, room, dorm, date, availability);
        return "redirect:/bookings";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("rooms", roomService.findAllRooms());
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("bodyContent", "booking/bookingForm");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String viewBooking(@PathVariable Long id, Model model) {
        Booking booking = bookingService.findBookingById(id);
        model.addAttribute("booking", booking);
        model.addAttribute("students", studentService.findAllStudents());
        model.addAttribute("rooms", roomService.findAllRooms());
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("bodyContent", "booking/bookingDetails");
        return "master-template";
    }


    @PostMapping("/edit/{id}")
    public String updateBooking(
            @PathVariable Long id,
            @RequestParam Long studentId,
            @RequestParam Long roomId,
            @RequestParam Long dormId,
            @RequestParam String bookingDate,
            @RequestParam BookingAvailability availability) {

        Student student = studentService.findStudentById(studentId);
        Room room = roomService.findRoomById(roomId);
        Dorm dorm = dormService.findDormById(dormId);
        LocalDate date = LocalDate.parse(bookingDate);
        bookingService.updateBooking(id, student, room, dorm, date, availability);
        return "redirect:/bookings";
    }

    @PostMapping("/delete/{id}")
    public String deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return "redirect:/bookings";
    }

    @PostMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/bookings";
    }
}