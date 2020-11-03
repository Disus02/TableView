package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ConnectionUtil;
import sample.Models.ModelCourses;
import sample.Models.ModelLessons;
import sample.Models.ModelTeachers;
import sample.Models.ModelUsers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ControllerDataBase {
    Connection conn;
    public ControllerDataBase() throws ClassNotFoundException {conn = ConnectionUtil.conDB();}

    ObservableList<ModelCourses> listCourses = FXCollections.observableArrayList();

    ObservableList<ModelLessons> listLessons = FXCollections.observableArrayList();

    ObservableList<ModelTeachers> listTeachers = FXCollections.observableArrayList();

    ObservableList<ModelUsers> listUsers = FXCollections.observableArrayList();

    @FXML
    private TableView<ModelCourses> tableCourses;
    @FXML
    private TableColumn<ModelCourses,String> coursesId;
    @FXML
    private TableColumn<ModelCourses,String> coursesTitle;
    @FXML
    private TableColumn<ModelCourses,String> coursesLength;
    @FXML
    private TableColumn<ModelCourses,String> coursesDescription;






    @FXML
    private TableView<ModelLessons> tableLessons;
    @FXML
    private TableColumn<ModelLessons,String> lessonsId;
    @FXML
    private TableColumn<ModelLessons,String> lessonsTeacher;
    @FXML
    private TableColumn<ModelLessons,String> lessonsCourses;
    @FXML
    private TableColumn<ModelLessons,String> lessonsRoom;
    @FXML
    private TableColumn<ModelLessons,String> lessonsDate;






    @FXML
    private TableView<ModelTeachers> tableTeachers;
    @FXML
    private TableColumn<ModelTeachers,String> teachersId;
    @FXML
    private TableColumn<ModelTeachers,String> teachersName;
    @FXML
    private TableColumn<ModelTeachers,String> teachersAddr;
    @FXML
    private TableColumn<ModelTeachers,String> teachersPhone;





    @FXML
    private TableView<ModelUsers> tableUser;
    @FXML
    private TableColumn<ModelUsers,String> userId;
    @FXML
    private TableColumn<ModelUsers,String> userFirstName;
    @FXML
    private TableColumn<ModelUsers,String> userLastName;
    @FXML
    private TableColumn<ModelUsers,String> userEmail;
    @FXML
    private TableColumn<ModelUsers,String> userLogin;
    @FXML
    private TableColumn<ModelUsers,String> userPassword;




    @FXML
    public void initialize() {
        try {
            String coursesSQL = "SELECT * FROM courses";
            PreparedStatement coursesStatement = conn.prepareStatement(coursesSQL);
            ResultSet resultCourses = coursesStatement.executeQuery();
            while (resultCourses.next()) {
                listCourses.add(new ModelCourses(
                        resultCourses.getString(1),
                        resultCourses.getString(2),
                        resultCourses.getString(3),
                        resultCourses.getString(4)));
            }
            coursesId.setCellValueFactory(new PropertyValueFactory<>("id"));
            coursesTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            coursesLength.setCellValueFactory(new PropertyValueFactory<>("length"));
            coursesDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

            tableCourses.setItems(listCourses);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        try {
            String sql = "SELECT * FROM lessons";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listLessons.add(new ModelLessons(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)));
            }
            lessonsId.setCellValueFactory(new PropertyValueFactory<>("id"));
            lessonsTeacher.setCellValueFactory(new PropertyValueFactory<>("teacher"));
            lessonsCourses.setCellValueFactory(new PropertyValueFactory<>("courses"));
            lessonsRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
            lessonsDate.setCellValueFactory(new PropertyValueFactory<>("date"));

            tableLessons.setItems(listLessons);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }




        try {
            String sql = "SELECT * FROM teachers";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listTeachers.add(new ModelTeachers(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)));
            }
            teachersId.setCellValueFactory(new PropertyValueFactory<>("id"));
            teachersName.setCellValueFactory(new PropertyValueFactory<>("name"));
            teachersAddr.setCellValueFactory(new PropertyValueFactory<>("addr"));
            teachersPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

            tableTeachers.setItems(listTeachers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            String sql = "SELECT * FROM users";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                listUsers.add(new ModelUsers(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)));
            }
            userId.setCellValueFactory(new PropertyValueFactory<>("id"));
            userFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
            userLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
            userEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            userLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
            userPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

            tableUser.setItems(listUsers);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
