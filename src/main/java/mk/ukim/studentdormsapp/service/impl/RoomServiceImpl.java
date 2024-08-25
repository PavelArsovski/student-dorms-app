package mk.ukim.studentdormsapp.service.impl;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.RoomType;
import mk.ukim.studentdormsapp.model.exception.InvalidRoomException;
import mk.ukim.studentdormsapp.repository.RoomRepository;
import mk.ukim.studentdormsapp.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room findRoomById(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new InvalidRoomException("Room with ID " + id + " not found."));
    }

    @Override
    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> findRoomsByDorm(Dorm dorm) {
        return roomRepository.findByDorm(dorm);
    }

    @Override
    public List<Room> findRoomsByAvailability(Boolean available) {
        return roomRepository.findByAvailable(available);
    }

    @Override
    public List<Room> findRoomsByType(RoomType roomType) {
        return roomRepository.findByRoomType(roomType);
    }

    @Override
    public Room createRoom(String roomNumber, Integer capacity, Dorm dorm, RoomType roomType, Boolean available) {
        roomRepository.findByRoomNumberAndDorm(roomNumber, dorm)
                .ifPresent(existingRoom -> {
                    throw new InvalidRoomException("Room with number " + roomNumber + " already exists in dorm " + dorm.getName());
                });

        Room room = new Room(null, roomNumber, capacity, dorm, roomType, available);
        return roomRepository.save(room);
    }

    @Override
    public Room updateRoom(Long id, String roomNumber, Integer capacity, Dorm dorm, RoomType roomType, Boolean available) {
        Room room = findRoomById(id);
        roomRepository.findByRoomNumberAndDorm(roomNumber, dorm)
                .ifPresent(existingRoom -> {
                    if (!existingRoom.getId().equals(id)) {
                        throw new InvalidRoomException("Room with number " + roomNumber + " already exists in dorm " + dorm.getName());
                    }
                });

        room.setRoomNumber(roomNumber);
        room.setCapacity(capacity);
        room.setDorm(dorm);
        room.setRoomType(roomType);
        room.setAvailable(available);
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public Room changeRoomAvailability(Long id, Boolean available) {
        Room room = findRoomById(id);
        room.setAvailable(available);
        return roomRepository.save(room);
    }

    @Override
    public List<Room> findRoomsWithAvailableCapacity(Integer minCapacity) {
        return roomRepository.findByCapacity(minCapacity);
    }
}