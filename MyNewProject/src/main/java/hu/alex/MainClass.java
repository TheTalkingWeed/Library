package hu.alex;

import java.io.IOException;
import java.sql.SQLException;

import hu.alex.LibrarianPac.JpaLibrarianDAO;
import hu.alex.LibrarianPac.Librarian;
import hu.alex.LibrarianPac.LibrarianDAO;
import hu.alex.UserPac.JpaUserDAO;
import hu.alex.UserPac.User;
import hu.alex.UserPac.UserDAO;
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
        UserDAO userDAO = new JpaUserDAO();
        User user = new User();

        user.setEmail("valami");
        user.setFname("test1");
        user.setLname("test1");

        userDAO.saveUser(user);

        LibrarianDAO librarianDAO = new JpaLibrarianDAO();
        Librarian librarian = new Librarian();

        librarian.setFname("test1");
        librarian.setLname("test2");
        librarian.setLoginPassword("asd");
        librarian.setLoginUsername("asd");

        librarianDAO.saveLibrarian(librarian);
        launch();
        stopDatabase();
    }

    private static Server s = new Server();


    private static void startDatabase() throws SQLException {
        new Server().runTool("-tcp", "-web", "-ifNotExists");
    }

    private static void stopDatabase()  {
        s.shutdown();
    }
}
