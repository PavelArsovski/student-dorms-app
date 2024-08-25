package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Maintenance;
import mk.ukim.studentdormsapp.model.Room;
import mk.ukim.studentdormsapp.model.enumeration.MaintenanceStatus;
import mk.ukim.studentdormsapp.service.MaintenanceService;
import mk.ukim.studentdormsapp.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceService maintenanceService;
    private final RoomService roomService;

    public MaintenanceController(MaintenanceService maintenanceService, RoomService roomService) {
        this.maintenanceService = maintenanceService;
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllMaintenanceRequests(Model model) {
        List<Maintenance> maintenanceRequests = maintenanceService.findAllMaintenanceRequests();
        model.addAttribute("maintenanceRequests", maintenanceRequests);
        model.addAttribute("bodyContent", "maintenance/maintenances");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getMaintenanceById(@PathVariable Long id, Model model) {
        Maintenance maintenance = maintenanceService.findMaintenanceById(id);
        model.addAttribute("maintenance", maintenance);
        model.addAttribute("bodyContent", "maintenance/maintenanceDetails");
        return "master-template";
    }

    @GetMapping("/create")
    public String showCreateMaintenanceForm(Model model) {
        model.addAttribute("maintenance", new Maintenance());
        model.addAttribute("rooms", roomService.findAllRooms());
        model.addAttribute("bodyContent", "maintenance/maintenanceForm");
        return "master-template";
    }

    @PostMapping("/create")
    public String createMaintenance(@ModelAttribute Maintenance maintenance, @RequestParam Long roomId) {
        Room room = roomService.findRoomById(roomId);
        maintenance.setRoom(room);
        maintenanceService.createMaintenance(maintenance.getRoom(), maintenance.getDate(), maintenance.getStatus(), maintenance.getDescription());
        return "redirect:/maintenance";
    }

    @GetMapping("/edit/{id}")
    public String showEditMaintenanceForm(@PathVariable Long id, Model model) {
        Maintenance maintenance = maintenanceService.findMaintenanceById(id);
        model.addAttribute("maintenance", maintenance);
        model.addAttribute("rooms", roomService.findAllRooms());
        model.addAttribute("bodyContent", "maintenance/maintenanceForm");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String editMaintenance(@PathVariable Long id, @ModelAttribute Maintenance maintenance, @RequestParam Long roomId) {
        Room room = roomService.findRoomById(roomId);
        maintenance.setRoom(room);
        maintenanceService.updateMaintenance(id, maintenance.getRoom(), maintenance.getDate(), maintenance.getStatus(), maintenance.getDescription());
        return "redirect:/maintenance";
    }

    @PostMapping("/delete/{id}")
    public String deleteMaintenance(@PathVariable Long id) {
        maintenanceService.deleteMaintenance(id);
        return "redirect:/maintenance";
    }

    @PostMapping("/change-status/{id}")
    public String changeMaintenanceStatus(@PathVariable Long id, @RequestParam MaintenanceStatus status) {
        maintenanceService.changeMaintenanceStatus(id, status);
        return "redirect:/maintenance";
    }
}
