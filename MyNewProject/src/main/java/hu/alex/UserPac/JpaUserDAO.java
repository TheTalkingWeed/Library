package hu.alex.UserPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaUserDAO implements UserDAO {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPersistence");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void saveUser(User u) {
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
    }

    @Override
    public void deleteUser(User u) {
        em.getTransaction().begin();
        em.remove(u);
        em.getTransaction().commit();
    }

    @Override
    public void updateUser(User u) {
        saveUser(u);
    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> query = em.createQuery(
                "select u from User u", User.class
        );
        List<User> users = query.getResultList();
        return users;
    }

    @Override
    public void close() throws Exception {
        emf.close();
        em.close();
    }
}
