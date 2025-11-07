package web.service;

import org.springframework.stereotype.Service;
import web.entity.User;
import web.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repo;
    public UserServiceImpl(UserRepository repo) { this.repo = repo; }

    @Override public List<User> findAll() { return repo.findAll(); }
    @Override public User findById(Long id) { return repo.findById(id); }
    @Override public User save(User u) { return repo.save(u); }
    @Override public void deleteById(Long id) { repo.deleteById(id); }
}
