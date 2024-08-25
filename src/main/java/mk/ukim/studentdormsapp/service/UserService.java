package mk.ukim.studentdormsapp.service;

import mk.ukim.studentdormsapp.model.enumeration.Role;
import mk.ukim.studentdormsapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User create(String username, String password, Role role);
}