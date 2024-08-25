package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.RoomType;

import java.util.List;

public interface RoomService {

    Room findRoomById(Long id);

    List<Room> findAllRooms();

    List<Room> findRoomsByDorm(Dorm dorm);

    List<Room> findRoomsByAvailability(Boolean available);

    List<Room> findRoomsByType(RoomType roomType);

    Room createRoom(String roomNumber, Integer capacity, Dorm dorm, RoomType roomType, Boolean available);

    Room updateRoom(Long id, String roomNumber, Integer capacity, Dorm dorm, RoomType roomType, Boolean available);

    void deleteRoom(Long id);

    Room changeRoomAvailability(Long id, Boolean available);

    List<Room> findRoomsWithAvailableCapacity(Integer minCapacity);
}