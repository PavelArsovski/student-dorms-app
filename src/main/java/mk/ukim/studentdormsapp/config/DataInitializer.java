package mk.ukim.studentdormsapp.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.studentdormsapp.model.User;
import mk.ukim.studentdormsapp.model.enumeration.Role;
import mk.ukim.studentdormsapp.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    public static final String ADMIN = "admin";
    public static final String USER = "user";
    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initData() {
        User admin = this.userService.create(ADMIN, ADMIN, Role.ROLE_ADMIN);
        User user = this.userService.create(USER, USER, Role.ROLE_USER);
    }
}
