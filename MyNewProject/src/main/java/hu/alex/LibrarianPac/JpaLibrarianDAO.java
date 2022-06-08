package hu.alex.LibrarianPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaLibrarianDAO implements LibrarianDAO{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPersistence");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void saveLibrarian(Librarian l) {
        em.getTransaction().begin();
        em.persist(l);
        em.getTransaction().commit();
    }

    @Override
    public void deleteLibrarian(Librarian l) {
        em.getTransaction().begin();
        em.remove(l);
        em.getTransaction().commit();
    }

    @Override
    public void updateLibrarian(Librarian l) {
        saveLibrarian(l);
    }

    @Override
    public List<Librarian> getLibrarians() {
        TypedQuery<Librarian> query = em.createQuery(
                "select l from Librarian l",Librarian.class
        );
        return query.getResultList();
    }

    @Override
    public void close() {
        emf.close();
        em.close();
    }
}
