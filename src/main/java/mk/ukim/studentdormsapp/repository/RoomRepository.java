package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByDorm(Dorm dorm);

    List<Room> findByRoomType(RoomType roomType);

    List<Room> findByAvailable(Boolean available);

    List<Room> findByCapacity(Integer capacity);

    Optional<Room> findByRoomNumberAndDorm(String roomNumber, Dorm dorm);
}