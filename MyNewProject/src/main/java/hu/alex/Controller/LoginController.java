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
import org.apache.commons.validator.routines.EmailValidator;


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
    @FXML
    private PasswordField userRegPassword;
    @FXML
    private PasswordField userRegPasswordAgain;
    @FXML
    private TextField userRegEmail;
    @FXML
    private TextField userRegFname;
    @FXML
    private TextField userRegLname;
    @FXML
    private TextField userRegUsername;


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

    public void UserRegisterButtonClicked(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/user_register_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
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

    public void BackFromUserRegister(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/fxml/user_login_interface.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void UserRegisterSubmit(ActionEvent event) throws IOException {
        String fname = userRegFname.getText();
        String lname = userRegLname.getText();
        String email = userRegEmail.getText();
        String username = userRegUsername.getText();
        String pass = userRegPassword.getText();
        String passagain = userRegPasswordAgain.getText();



        if (checkForEmptyInput(fname,lname,email,username,pass,passagain)){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Register warning");
            alert.setContentText("Please fill every fields!");

            alert.showAndWait();
        }else{
            User checkForEmail = users.stream().filter(user -> email.equals(user.getEmail())).findAny().orElse(null);
            User checkForUsername = users.stream().filter(user -> username.equals(user.getUsername())).findAny().orElse(null);
            if (checkForEmail != null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Register warning");
                alert.setContentText("Email already registered");

                alert.showAndWait();
            }
            if (checkForUsername != null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Register warning");
                alert.setContentText("Username already registered");

                alert.showAndWait();
            }
            if (!isValidEmail(email)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Register warning");
                alert.setContentText("Invalid email!");

                alert.showAndWait();
            }
            if (!pass.equals(passagain)){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Register warning");
                alert.setContentText("Passwords don't match!");

                alert.showAndWait();
            }


            User user = new User();
            user.setFname(fname);
            user.setLname(lname);
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(pass);

            userDAO.saveUser(user);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Register information");
            alert.setContentText("Registered successfully");

            alert.showAndWait();

            root = FXMLLoader.load(getClass().getResource("/fxml/user_login_interface.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

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

    private boolean checkForEmptyInput(String fname,String lname,String email,String username,String pass,String passagain){
        //return true if one or more fields are empty
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || username.isEmpty() || pass.isEmpty() || passagain.isEmpty()){
            return true;
        }

        return false;
    }

    private boolean isValidEmail(String email){
        EmailValidator validator = EmailValidator.getInstance();

        return validator.isValid(email);
    }
}
