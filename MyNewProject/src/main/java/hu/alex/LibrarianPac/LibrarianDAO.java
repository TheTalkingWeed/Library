package hu.alex.LibrarianPac;

import java.util.List;

public interface LibrarianDAO extends AutoCloseable{
    public void saveLibrarian(Librarian l);
    public void deleteLibrarian(Librarian l);
    public void updateLibrarian(Librarian l);
    public List<Librarian> getLibrarians();
}
