package mk.ukim.studentdormsapp.model;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.studentdormsapp.model.enumeration.Role;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "booking_user")
public class User {

    @Id
    private String username;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;
}