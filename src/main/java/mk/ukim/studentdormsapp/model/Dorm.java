package mk.ukim.studentdormsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dorm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private Integer freshmanCapacity;

    private Integer totalCapacity;

    private String imageUrl;

    @OneToMany(mappedBy = "dorm", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Room> rooms;
}