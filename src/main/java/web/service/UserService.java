package web.service;

import web.entity.User;
import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    User save(User u);
    void deleteById(Long id);
}
