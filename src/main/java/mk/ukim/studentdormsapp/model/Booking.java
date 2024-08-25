package mk.ukim.studentdormsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.studentdormsapp.model.enumeration.BookingAvailability;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "dorm_id", nullable = false)
    private Dorm dorm;

    @ManyToOne
    @JoinColumn(name = "room_number", nullable = false)
    private Room room;

    private LocalDate bookingDate;

    @Enumerated(EnumType.STRING)
    private BookingAvailability availability;
}