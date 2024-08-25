package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Dorm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DormService {

    Dorm findDormById(Long dormId);

    List<Dorm> findAllDorms();

    Dorm createDorm(String name, String address, Integer freshmanCapacity, Integer totalCapacity, String imageUrl);

    Dorm updateDorm(Long dormId, String name, String address, Integer freshmanCapacity, Integer totalCapacity, String imageUrl);

    void deleteDorm(Long dormId);

    Integer calculateAvailableCapacity(Long dormId);

    Dorm findDormByName(String name);

    List<Dorm> findDormsByFreshmanCapacity(Integer capacity);

    List<Dorm> findDormsByTotalCapacity(Integer capacity);

    List<Dorm> findDormsWithAvailableRooms();

    List<Dorm> findDormsByRoomType(String roomType);

    List<Dorm> findDormsByRoomCapacity(Integer capacity);
}