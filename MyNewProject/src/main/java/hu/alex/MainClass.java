package hu.alex;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.function.BinaryOperator;


import hu.alex.BookPac.Book;
import hu.alex.BookPac.BookDAO;
import hu.alex.BookPac.JpaBookDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.h2.tools.Server;

public class MainClass extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login_interface.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        startDatabase();

//        BookDAO bookDAO = new JpaBookDAO();
//        Book book = new Book();
//
//        book.setAuthor("Dmitry Glukhovsky");
//        book.setTitle("Metro 2033");
//        book.setReleaseDate(LocalDate.of(2010,3,18));
//        book.setPageNumber(458);
//        book.setQuantity(100);
//        book.setGenre("Post-apocalyptic");
//
//        bookDAO.saveBook(book);
//        UserDAO userDAO = new JpaUserDAO();
//        User user = new User();
//
//        user.setEmail("valami");
//        user.setFname("test1");
//        user.setLname("test1");
//        user.setUsername("user1");
//        user.setPassword("user1");
//
//        userDAO.saveUser(user);
//
//        LibrarianDAO librarianDAO = new JpaLibrarianDAO();
//        Librarian librarian = new Librarian();
//
//        librarian.setFname("test1");
//        librarian.setLname("test2");
//        librarian.setLoginPassword("asd");
//        librarian.setLoginUsername("asd");
//
//        librarianDAO.saveLibrarian(librarian);
        launch(args);
        stopDatabase();
    }

    private static Server s = new Server();

    private static void startDatabase() throws SQLException {
        s.runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
