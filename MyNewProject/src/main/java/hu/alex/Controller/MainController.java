package hu.alex.Controller;

import javafx.fxml.FXML;
import hu.alex.BookPac.Book;
import hu.alex.BookPac.BookDAO;
import hu.alex.BookPac.JpaBookDAO;
import hu.alex.UserPac.JpaUserDAO;
import hu.alex.UserPac.User;
import hu.alex.UserPac.UserDAO;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private ListView<String> authorListView;

    @FXML
    private ListView<String> genreListView;

    @FXML
    private ListView<Integer> pageNumberListView;

    @FXML
    private ListView<Integer> quantityListView;

    @FXML
    private ListView<LocalDate> releaseDateListView;

    @FXML
    private ListView<String> titleListView;

    @FXML
    private ListView<String> userEmailListView;

    @FXML
    private ListView<String> userFirstNameListView;

    @FXML
    private ListView<String> userLastNameListView;

    UserDAO userDAO = new JpaUserDAO();
    private List<User> users = new ArrayList<>(userDAO.getUsers());

    BookDAO bookDAO = new JpaBookDAO();
    private List<Book> books = new ArrayList<>(bookDAO.getBooks());

    public void addBookButtonPushed(javafx.event.ActionEvent event) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/book_register_interface.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Book register");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
        showUsers();
    }

    private void showUsers(){
        for (User u:users) {
            userEmailListView.getItems().add(u.getEmail());
            userFirstNameListView.getItems().add(u.getFname());
            userLastNameListView.getItems().add(u.getLname());
        }
    }

    private void showBooks() {
        for (Book b:books) {
            authorListView.getItems().add(b.getAuthor());
            genreListView.getItems().add(b.getGenre());
            pageNumberListView.getItems().add(b.getPageNumber());
            quantityListView.getItems().add(b.getQuantity());
            releaseDateListView.getItems().add(b.getReleaseDate());
            titleListView.getItems().add(b.getTitle());
        }
    }



}
