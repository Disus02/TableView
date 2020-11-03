package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.io.IOException;
import java.sql.*;

public class ControllerRegistration {

    Connection conn;

    public ControllerRegistration() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtLogin;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label labelLogin;
    @FXML
    private Label labelEmail;
    @FXML
    private Button buttonToSignIn;
    @FXML
    private Label status;

    @FXML
    private void pressReg(ActionEvent event) throws SQLException {
        String login = txtLogin.getText();
        String email = txtEmail.getText();
        String sql1 = "SELECT * FROM users WHERE login=? and email=?";
        PreparedStatement statement1 = conn.prepareStatement(sql1);
        statement1.setString(1,login);
        statement1.setString(2,email);
        ResultSet resultSet1 = statement1.executeQuery();
        while (resultSet1.next()){
           String checkLogin = resultSet1.getString("login");
            String checkEmail = resultSet1.getString("password");
            if (!login.equals(checkLogin) && !email.equals(checkEmail)) {
                labelEmail.setText("");
                labelLogin.setText("");
                String sql = "INSERT INTO users(firstName, lastName, email, login, password)VALUES(?, ?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, txtFirstName.getText());
                statement.setString(2, txtLastName.getText());
                statement.setString(3, txtEmail.getText());
                statement.setString(4, txtLogin.getText());
                statement.setString(5, txtPassword.getText());
                int result = statement.executeUpdate();
                if (result == 1) {
                    ResultSet resultSet = statement.getGeneratedKeys();
                    while (resultSet.next()) {
                        int id = resultSet.getInt(1);
                        status.setText(txtFirstName.getText() + " " + txtLastName.getText() + " зарегистрирован id = " + id);
                    }
                }
            }
            if (login.equals(checkLogin)){
                labelLogin.setText("Логин уже существует");
                labelEmail.setText("");
            }
            if (email.equals(checkEmail)){
                labelEmail.setText("Email уже существует");
                labelLogin.setText("");
            }
            if (email.equals(checkEmail) && login.equals(checkLogin)){
                labelLogin.setText("Логин уже существует");
                labelEmail.setText("Email уже существует");
            }
        }
    }
    @FXML
    private void pressToSignIn(ActionEvent event){
        buttonToSignIn.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/signIn.fxml"));
            primaryStage.setTitle("Вход");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
