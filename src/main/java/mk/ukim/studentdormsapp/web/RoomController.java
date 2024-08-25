package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.RoomType;
import mk.ukim.studentdormsapp.service.DormService;
import mk.ukim.studentdormsapp.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;
    private final DormService dormService;

    public RoomController(RoomService roomService, DormService dormService) {
        this.roomService = roomService;
        this.dormService = dormService;
    }

    @GetMapping
    public String getAllRooms(
            @RequestParam(required = false) String dormName,
            Model model) {
        List<Room> rooms;
        if (dormName != null && !dormName.isEmpty()) {
            Dorm dorm = dormService.findDormByName(dormName);
            rooms = roomService.findRoomsByDorm(dorm);
        } else {
            rooms = roomService.findAllRooms();
        }
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "room/rooms");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getRoomById(@PathVariable Long id, Model model) {
        Room room = roomService.findRoomById(id);
        model.addAttribute("room", room);
        model.addAttribute("bodyContent", "room/roomDetails");
        return "master-template";
    }

    @GetMapping("/dorm")
    public String getRoomsByDorm(@RequestParam Long dormId, Model model) {
        Dorm dorm = dormService.findDormById(dormId);
        List<Room> rooms = roomService.findRoomsByDorm(dorm);
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "room/rooms");
        return "master-template";
    }

    @GetMapping("/type")
    public String getRoomsByType(@RequestParam RoomType roomType, Model model) {
        List<Room> rooms = roomService.findRoomsByType(roomType);
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "room/rooms");
        return "master-template";
    }

    @GetMapping("/availability")
    public String getRoomsByAvailability(@RequestParam Boolean available, Model model) {
        List<Room> rooms = roomService.findRoomsByAvailability(available);
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "room/rooms");
        return "master-template";
    }

    @GetMapping("/capacity")
    public String getRoomsByCapacity(@RequestParam Integer minCapacity, Model model) {
        List<Room> rooms = roomService.findRoomsWithAvailableCapacity(minCapacity);
        model.addAttribute("rooms", rooms);
        model.addAttribute("bodyContent", "room/rooms");
        return "master-template";
    }

    @GetMapping("/create")
    public String showCreateRoomForm(Model model) {
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("bodyContent", "room/roomForm");
        return "master-template";
    }

    @PostMapping("/create")
    public String createRoom(@ModelAttribute Room room, @RequestParam Long dormId) {
        Dorm dorm = dormService.findDormById(dormId);
        room.setDorm(dorm);
        roomService.createRoom(room.getRoomNumber(), room.getCapacity(), dorm, room.getRoomType(), room.getAvailable());
        return "redirect:/rooms";
    }

    @GetMapping("/edit/{id}")
    public String showEditRoomForm(@PathVariable Long id, Model model) {
        Room room = roomService.findRoomById(id);
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("room", room);
        model.addAttribute("bodyContent", "room/roomForm");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String editRoom(@PathVariable Long id, @ModelAttribute Room room, @RequestParam Long dormId) {
        Dorm dorm = dormService.findDormById(dormId);
        roomService.updateRoom(id, room.getRoomNumber(), room.getCapacity(), dorm, room.getRoomType(), room.getAvailable());
        return "redirect:/rooms";
    }

    @PostMapping("/delete/{id}")
    public String deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return "redirect:/rooms";
    }

    @PostMapping("/change-availability/{id}")
    public String changeRoomAvailability(@PathVariable Long id, @RequestParam Boolean available) {
        roomService.changeRoomAvailability(id, available);
        return "redirect:/rooms";
    }
}