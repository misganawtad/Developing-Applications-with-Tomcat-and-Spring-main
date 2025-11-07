package web.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    public List<User> findAll() {
        return em.createQuery("select u from User u order by u.id", User.class).getResultList();
    }

    public User findById(Long id) { return em.find(User.class, id); }

    @Transactional
    public User save(User u) {
        if (u.getId() == null) {
            em.persist(u);
            return u;
        } else {
            return em.merge(u);
        }
    }

    @Transactional
    public void deleteById(Long id) {
        User u = em.find(User.class, id);
        if (u != null) em.remove(u);
    }
}
