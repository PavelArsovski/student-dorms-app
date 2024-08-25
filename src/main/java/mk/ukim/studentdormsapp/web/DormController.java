package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.service.DormService;
import mk.ukim.studentdormsapp.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/dorms")
public class DormController {

    private final DormService dormService;
    private final RoomService roomService;
    private static final String UPLOAD_DIR = "static/images/";

    public DormController(DormService dormService, RoomService roomService) {
        this.dormService = dormService;
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllDorms(Model model) {
        List<Dorm> dorms = dormService.findAllDorms();
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getDormById(@PathVariable Long id, Model model) {
        Dorm dorm = dormService.findDormById(id);
        model.addAttribute("dorm", dorm);
        model.addAttribute("bodyContent", "dorm/dormDetails");
        return "master-template";
    }

    @GetMapping("/create")
    public String showCreateDormForm(Model model) {
        model.addAttribute("dorm", new Dorm());
        model.addAttribute("bodyContent", "dorm/dormForm");
        return "master-template";
    }

    @PostMapping("/create")
    public String createDorm(@RequestParam String name,
                             @RequestParam String address,
                             @RequestParam Integer freshmanCapacity,
                             @RequestParam Integer totalCapacity,
                             @RequestParam("image") MultipartFile image) {
        String imageUrl = saveImage(image);
        dormService.createDorm(name, address, freshmanCapacity, totalCapacity, imageUrl);
        return "redirect:/dorms";
    }

    @GetMapping("/edit/{id}")
    public String showEditDormForm(@PathVariable Long id, Model model) {
        Dorm dorm = dormService.findDormById(id);
        model.addAttribute("dorm", dorm);
        model.addAttribute("bodyContent", "dorm/dormForm");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String editDorm(@PathVariable Long id,
                           @ModelAttribute Dorm dorm,
                           @RequestParam("image") MultipartFile image) {
        String imageUrl = dorm.getImageUrl();
        if (!image.isEmpty()) {
            imageUrl = saveImage(image);
        }
        dormService.updateDorm(id, dorm.getName(), dorm.getAddress(), dorm.getFreshmanCapacity(), dorm.getTotalCapacity(), imageUrl);
        return "redirect:/dorms";
    }

    @PostMapping("/delete/{id}")
    public String deleteDorm(@PathVariable Long id) {
        dormService.deleteDorm(id);
        return "redirect:/dorms";
    }

    @GetMapping("/available-capacity/{id}")
    public String getAvailableCapacity(@PathVariable Long id, Model model) {
        Integer availableCapacity = dormService.calculateAvailableCapacity(id);
        model.addAttribute("availableCapacity", availableCapacity);
        model.addAttribute("bodyContent", "dorm/dormDetails");
        return "master-template";
    }

    @GetMapping("/search-by-name")
    public String getDormByName(@RequestParam String name, Model model) {
        Dorm dorm = dormService.findDormByName(name);
        model.addAttribute("dorm", dorm);
        model.addAttribute("bodyContent", "dorm/dormDetails");
        return "master-template";
    }

    @GetMapping("/freshman-capacity")
    public String getDormsByFreshmanCapacity(@RequestParam Integer capacity, Model model) {
        List<Dorm> dorms = dormService.findDormsByFreshmanCapacity(capacity);
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
        return "master-template";
    }

    @GetMapping("/total-capacity")
    public String getDormsByTotalCapacity(@RequestParam Integer capacity, Model model) {
        List<Dorm> dorms = dormService.findDormsByTotalCapacity(capacity);
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
        return "master-template";
    }

    @GetMapping("/available-rooms")
    public String getDormsWithAvailableRooms(Model model) {
        List<Dorm> dorms = dormService.findDormsWithAvailableRooms();
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
        return "master-template";
    }

    @GetMapping("/room-type")
    public String getDormsByRoomType(@RequestParam String roomType, Model model) {
        List<Dorm> dorms = dormService.findDormsByRoomType(roomType);
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
        return "master-template";
    }

    @GetMapping("/room-capacity")
    public String getDormsByRoomCapacity(@RequestParam Integer capacity, Model model) {
        List<Dorm> dorms = dormService.findDormsByRoomCapacity(capacity);
        model.addAttribute("dorms", dorms);
        model.addAttribute("bodyContent", "dorm/dorms");
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