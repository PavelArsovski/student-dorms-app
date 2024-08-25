package mk.ukim.studentdormsapp.repository;

import mk.ukim.studentdormsapp.model.Dorm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DormRepository extends JpaRepository<Dorm, Long> {

    Optional<Dorm> findByName(String name);

    List<Dorm> findAllByFreshmanCapacityGreaterThanEqual(Integer capacity);

    List<Dorm> findAllByTotalCapacityGreaterThanEqual(Integer capacity);

    List<Dorm> findAllByRooms_AvailableTrue();

    List<Dorm> findAllByRooms_RoomType(String roomType);

    List<Dorm> findAllByRooms_Capacity(Integer capacity);
}