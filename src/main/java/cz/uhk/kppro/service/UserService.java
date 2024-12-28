package cz.uhk.kppro.service;

import cz.uhk.kppro.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    void save(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}