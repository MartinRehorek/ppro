package cz.uhk.kppro.service;

import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);
    List<User> getAllUsers();
    User getUserById(long id);
    void deleteUserById(long id);
    void save(User user);
}