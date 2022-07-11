package hu.alex.Controller;

import hu.alex.BookPac.Book;
import hu.alex.BookPac.BookDAO;
import hu.alex.BookPac.JpaBookDAO;
import hu.alex.UserPac.JpaUserDAO;
import hu.alex.UserPac.User;
import hu.alex.UserPac.UserDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    UserDAO userDAO = new JpaUserDAO();
    private List<User> users = new ArrayList<>(userDAO.getUsers());

    BookDAO bookDAO = new JpaBookDAO();
    private List<Book> books = new ArrayList<>(bookDAO.getBooks());



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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showBooks();
    }
}
