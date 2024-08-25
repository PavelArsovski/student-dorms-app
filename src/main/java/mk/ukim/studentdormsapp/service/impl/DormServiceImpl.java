package mk.ukim.studentdormsapp.service.impl;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.exception.InvalidDormException;
import mk.ukim.studentdormsapp.repository.DormRepository;
import mk.ukim.studentdormsapp.service.DormService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class DormServiceImpl implements DormService {

    private final DormRepository dormRepository;

    public DormServiceImpl(DormRepository dormRepository) {
        this.dormRepository = dormRepository;
    }

    @Override
    public Dorm findDormById(Long dormId) {
        return dormRepository.findById(dormId)
                .orElseThrow(() -> new InvalidDormException("Dorm with ID " + dormId + " not found"));
    }

    @Override
    public List<Dorm> findAllDorms() {
        return dormRepository.findAll();
    }

    @Override
    public Dorm createDorm(String name, String address, Integer freshmanCapacity, Integer totalCapacity, String imageUrl) {
        Dorm dorm = new Dorm();
        dorm.setName(name);
        dorm.setAddress(address);
        dorm.setFreshmanCapacity(freshmanCapacity);
        dorm.setTotalCapacity(totalCapacity);
        dorm.setImageUrl(imageUrl);
        return dormRepository.save(dorm);
    }

    @Override
    public Dorm updateDorm(Long dormId, String name, String address, Integer freshmanCapacity, Integer totalCapacity, String imageUrl) {
        Dorm dorm = findDormById(dormId);
        dorm.setName(name);
        dorm.setAddress(address);
        dorm.setFreshmanCapacity(freshmanCapacity);
        dorm.setTotalCapacity(totalCapacity);
        dorm.setImageUrl(imageUrl);
        return dormRepository.save(dorm);
    }

    @Override
    public void deleteDorm(Long dormId) {
        Dorm dorm = findDormById(dormId);
        dormRepository.delete(dorm);
    }

    @Override
    public Integer calculateAvailableCapacity(Long dormId) {
        Dorm dorm = findDormById(dormId);
        return dorm.getRooms().stream()
                .filter(room -> Boolean.TRUE.equals(room.getAvailable()))
                .mapToInt(room -> room.getCapacity() != null ? room.getCapacity() : 0)
                .sum();
    }

    @Override
    public Dorm findDormByName(String name) {
        return dormRepository.findByName(name)
                .orElseThrow(() -> new InvalidDormException("Dorm with name " + name + " not found"));
    }

    @Override
    public List<Dorm> findDormsByFreshmanCapacity(Integer capacity) {
        return dormRepository.findAllByFreshmanCapacityGreaterThanEqual(capacity);
    }

    @Override
    public List<Dorm> findDormsByTotalCapacity(Integer capacity) {
        return dormRepository.findAllByTotalCapacityGreaterThanEqual(capacity);
    }

    @Override
    public List<Dorm> findDormsWithAvailableRooms() {
        return dormRepository.findAllByRooms_AvailableTrue();
    }

    @Override
    public List<Dorm> findDormsByRoomType(String roomType) {
        return dormRepository.findAllByRooms_RoomType(roomType);
    }

    @Override
    public List<Dorm> findDormsByRoomCapacity(Integer capacity) {
        return dormRepository.findAllByRooms_Capacity(capacity);
    }

    private String saveImage(MultipartFile image) {
        if (image.isEmpty()) {
            return null;
        }
        try {
            Path uploadDir = Paths.get("src/main/resources/static/images/");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(image.getOriginalFilename());
            Files.write(filePath, image.getBytes());

            return "/images/" + image.getOriginalFilename();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to save image: " + e.getMessage());
        }
    }
}