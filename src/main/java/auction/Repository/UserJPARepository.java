package auction.Repository;

import auction.Models.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Console;
import java.util.List;

public class UserJPARepository implements UserRepository {

    private EntityManager entityManager;

    public UserJPARepository(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public void create(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void edit(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void remove(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(this.entityManager.merge(user));
        this.entityManager.getTransaction().commit();
    }

    @Override
    public int count() {
        Query q = entityManager.createNamedQuery("User.count", User.class);

        int result = (int) q.getSingleResult();

        return result;
    }

    @Override
    public List<User> findAll() {
        Query q = entityManager.createNamedQuery("User.FindAll", User.class);

        List<User> result = q.getResultList();

        return result;
    }

    @Override
    public User findByEmail(String email) {
        Query q = entityManager.createNamedQuery("User.FindByEmail", User.class).setParameter("inputemail", email);

        try {
            return (User) q.getSingleResult();
        } catch (Exception ex) {
            System.out.println("A result has not been found for email address " + email);
            return null;
        }
    }
}
