package hu.alex.Controller;

import hu.alex.BookPac.Book;
import hu.alex.BookPac.BookDAO;
import hu.alex.BookPac.JpaBookDAO;
import hu.alex.UserPac.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddBookController {
    @FXML
    private TextField bookAuthor;

    @FXML
    private TextField bookGenre;

    @FXML
    private TextField bookPageNumber;

    @FXML
    private TextField bookQuantity;

    @FXML
    private DatePicker bookReleaseDate;

    @FXML
    private TextField bookTitle;

    BookDAO bookDAO = new JpaBookDAO();
    List<Book> books = new ArrayList<>(bookDAO.getBooks());


    public void addBook(javafx.event.ActionEvent event) {
        String author = bookAuthor.getText();
        String genre = bookGenre.getText();
        String pageNumber = bookPageNumber.getText();
        String quantity = bookQuantity.getText();
        LocalDate releaseDate = bookReleaseDate.getValue();
        String title = bookTitle.getText();
        if (checkForEmptyInput(author,genre,pageNumber,quantity,releaseDate,title)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error warning");
            alert.setContentText("Please fill all fields");

            alert.showAndWait();
        }
        if (!isNumber(pageNumber) && !pageNumber.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error warning");
            alert.setContentText("Page number cannot be a string!");

            alert.showAndWait();
        }
        if (!isNumber(quantity) && !quantity.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error warning");
            alert.setContentText("Quantity cannot be a string!");

            alert.showAndWait();
        }

        Book checkForTitle = books.stream().filter(book -> title.equals(book.getTitle())).findAny().orElse(null);

        if (checkForTitle != null){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error warning");
            alert.setContentText("This book is already registered!");

            alert.showAndWait();
        }


        Book book = new Book();
            book.setAuthor(author);
            book.setGenre(genre);
            book.setPageNumber(Integer.parseInt(pageNumber));
            book.setQuantity(Integer.parseInt(quantity));
            book.setReleaseDate(releaseDate);
            book.setTitle(title);

            bookDAO.saveBook(book);

    }

    private boolean checkForEmptyInput(String author,String genre,String pageNumber,String quantity,LocalDate releaseDate,String title){
        //return true if one or more fields are empty
        if (author.isEmpty() || genre.isEmpty() || pageNumber.isEmpty() || quantity.isEmpty() || releaseDate == null || title.isEmpty()){
            return true;
        }

        return false;
    }

    private boolean isNumber(String number){

        try{
            int intValue = Integer.parseInt(number);
            return true;
        }catch (NumberFormatException e){
            return false;
        }

    }



}
