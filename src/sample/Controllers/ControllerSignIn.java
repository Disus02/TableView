package sample.Controllers;

import com.sun.prism.PresentableState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sample.ConnectionUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerSignIn {

    Connection conn;

    public ControllerSignIn() throws ClassNotFoundException {conn = ConnectionUtil.conDB();
    }

    @FXML
    private Label status;
    @FXML
    private TextField txtLogin;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Button buttonToDataBase;
    @FXML
    private Button buttonToReg;

    @FXML
    public void pressToReg(ActionEvent event){
        buttonToReg.getScene().getWindow().hide();
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/registration.fxml"));
            primaryStage.setTitle("Регистрация");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void pressSignIn(ActionEvent event) throws SQLException {
        String checkLogin = "";
        String checkPass = "";
        String login = txtLogin.getText();
        String pass = txtPassword.getText();
        String sql = "SELECT * FROM users WHERE login=? and password=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,login);
        statement.setString(2,pass);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            checkLogin = resultSet.getString("login");
            checkPass = resultSet.getString("password");
            if (login.equals(checkLogin) && pass.equals(checkPass)){
                status.setText("Вы вошли");
                status.setTextFill(Color.web("#0076a3"));
                buttonToDataBase.setOnAction(event1 ->{
                    buttonToDataBase.getScene().getWindow().hide();
                    try {
                        Stage primaryStage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/sample/fxml/database.fxml"));
                        primaryStage.setTitle("База даныых");
                        primaryStage.setScene(new Scene(root));
                        primaryStage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }});
            }
        }
        if (!login.equals(checkLogin)|| !pass.equals(checkPass)){
            status.setText("Неверный логин или пароль");
        }

    }
}
