package hu.alex.Controller;

import hu.alex.LibrarianPac.JpaLibrarianDAO;
import hu.alex.LibrarianPac.Librarian;
import hu.alex.LibrarianPac.LibrarianDAO;
import hu.alex.UserPac.JpaUserDAO;
import hu.alex.UserPac.User;
import hu.alex.UserPac.UserDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private PasswordField librarianPasswordInput;

    @FXML
    private TextField librarianUserNameInput;


    @FXML
    private PasswordField userPasswordInput;

    @FXML
    private TextField userUsernameInput;

    UserDAO userDAO = new JpaUserDAO();
    private List<User> users = new ArrayList<>(userDAO.getUsers());

    LibrarianDAO librarianDAO = new JpaLibrarianDAO();
    private List<Librarian> librarians = new ArrayList<>(librarianDAO.getLibrarians());


    public void LibrarianLoginSceneButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/librarian_login_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void UserLoginSceneButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/user_login_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void BackFromUserLogin(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/login_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void BackFromLibrarianLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/login_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void LibrarianLoginButton(ActionEvent event) throws IOException {
        String username = librarianUserNameInput.getText();
        String password = librarianPasswordInput.getText();
        if (containsLibrarianUsername(username)){
            if (wasLibrarianLoginSuccessful(username,password)){
                root = FXMLLoader.load(getClass().getResource("/fxml/librarian_interface.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(true);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Login warning");
                alert.setContentText("Wrong password!");

                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Login warning");
            alert.setContentText("Username doesn't exist!");

            alert.showAndWait();
        }

    }

    private boolean containsLibrarianUsername(String username){
        for (Librarian l:librarians) {
            if (l.getLoginUsername().equals(username)) return true;
        }
        return false;
    }

    private boolean wasLibrarianLoginSuccessful(String username,String password){
        for (Librarian l:librarians) {
            if (l.getLoginUsername().equals(username) && l.getLoginPassword().equals(password)) return true;
        }
        return false;
    }

    public void UserLoginButton(ActionEvent event) throws IOException{
        String username = userUsernameInput.getText();
        String password = userPasswordInput.getText();
        if (containsUserUsername(username)){
            if (wasUserLoginSuccessful(username,password)){
                root = FXMLLoader.load(getClass().getResource("/fxml/user_interface.fxml"));
                stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(true);
                stage.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Login warning");
                alert.setContentText("Wrong password!");

                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Login warning");
            alert.setContentText("Username doesn't exist!");

            alert.showAndWait();
        }


    }

    private boolean containsUserUsername(String username){
        for (User u : users){
            if (u.getUsername().equals(username)) return true;
        }
        return false;
    }

    private boolean wasUserLoginSuccessful(String username,String password){
        for(User u: users){
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) return true;
        }

        return false;
    }




}
