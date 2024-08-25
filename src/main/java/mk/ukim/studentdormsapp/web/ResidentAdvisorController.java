package mk.ukim.studentdormsapp.web;

import mk.ukim.studentdormsapp.model.Dorm;
import mk.ukim.studentdormsapp.model.ResidentAdvisor;
import mk.ukim.studentdormsapp.service.DormService;
import mk.ukim.studentdormsapp.service.ResidentAdvisorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/resident-advisors")
public class ResidentAdvisorController {

    private final ResidentAdvisorService residentAdvisorService;
    private final DormService dormService;

    public ResidentAdvisorController(ResidentAdvisorService residentAdvisorService, DormService dormService) {
        this.residentAdvisorService = residentAdvisorService;
        this.dormService = dormService;
    }

    @GetMapping
    public String getAllResidentAdvisors(Model model) {
        List<ResidentAdvisor> residentAdvisors = residentAdvisorService.findAllResidentAdvisors();
        model.addAttribute("residentAdvisors", residentAdvisors);
        model.addAttribute("bodyContent", "resident-advisor/resident-advisors");
        return "master-template";
    }

    @GetMapping("/{id}")
    public String getResidentAdvisorById(@PathVariable Long id, Model model) {
        ResidentAdvisor residentAdvisor = residentAdvisorService.findResidentAdvisorById(id);
        model.addAttribute("residentAdvisor", residentAdvisor);
        model.addAttribute("bodyContent", "resident-advisor/resident-advisorDetails");
        return "master-template";
    }

    @GetMapping("/create")
    public String showCreateResidentAdvisorForm(Model model) {
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("residentAdvisor", new ResidentAdvisor());
        model.addAttribute("bodyContent", "resident-advisor/resident-advisorForm");
        return "master-template";
    }

    @PostMapping("/create")
    public String createResidentAdvisor(@ModelAttribute ResidentAdvisor residentAdvisor, @RequestParam Long dormId) {
        Dorm dorm = dormService.findDormById(dormId);
        residentAdvisorService.createResidentAdvisor(residentAdvisor.getName(), residentAdvisor.getSurname(), residentAdvisor.getEmail(), dorm);
        return "redirect:/resident-advisors";
    }

    @GetMapping("/edit/{id}")
    public String showEditResidentAdvisorForm(@PathVariable Long id, Model model) {
        ResidentAdvisor residentAdvisor = residentAdvisorService.findResidentAdvisorById(id);
        model.addAttribute("dorms", dormService.findAllDorms());
        model.addAttribute("residentAdvisor", residentAdvisor);
        model.addAttribute("bodyContent", "resident-advisor/resident-advisorForm");
        return "master-template";
    }

    @PostMapping("/edit/{id}")
    public String editResidentAdvisor(@PathVariable Long id, @ModelAttribute ResidentAdvisor residentAdvisor, @RequestParam Long dormId) {
        Dorm dorm = dormService.findDormById(dormId);
        residentAdvisorService.updateResidentAdvisor(id, residentAdvisor.getName(), residentAdvisor.getSurname(), residentAdvisor.getEmail(), dorm);
        return "redirect:/resident-advisors";
    }

    @PostMapping("/delete/{id}")
    public String deleteResidentAdvisor(@PathVariable Long id) {
        residentAdvisorService.deleteResidentAdvisor(id);
        return "redirect:/resident-advisors";
    }
}