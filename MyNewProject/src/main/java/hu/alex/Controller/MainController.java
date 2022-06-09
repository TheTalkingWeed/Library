package hu.alex.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDate;

public class MainController {
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

}
