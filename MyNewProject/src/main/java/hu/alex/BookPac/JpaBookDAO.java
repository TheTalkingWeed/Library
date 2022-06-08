package hu.alex.BookPac;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JpaBookDAO implements BookDAO{
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraryPersistence");
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void saveBook(Book b) {
        em.getTransaction().begin();
        em.persist(b);
        em.getTransaction().commit();
    }

    @Override
    public void deleteBook(Book b) {
        em.getTransaction().begin();
        em.remove(b);
        em.getTransaction().commit();
    }

    @Override
    public void updateBook(Book b) {
        saveBook(b);
    }

    @Override
    public List<Book> getBooks() {
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b",Book.class
        );
        List<Book> books = query.getResultList();
        return books;
    }

    @Override
    public void close() throws Exception {
        emf.close();
        em.close();
    }
}
