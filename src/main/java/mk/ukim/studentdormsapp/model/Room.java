package mk.ukim.studentdormsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.studentdormsapp.model.enumeration.RoomType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private Integer capacity;

    @ManyToOne
    @JoinColumn(name = "dorm_id", nullable = false)
    private Dorm dorm;

    @Enumerated(value = EnumType.STRING)
    private RoomType roomType;

    private Boolean available;
}