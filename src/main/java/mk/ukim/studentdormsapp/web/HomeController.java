package mk.ukim.studentdormsapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public String showHome(Model model) {
        model.addAttribute("bodyContent", "home/home");
        return "master-template";
    }
}