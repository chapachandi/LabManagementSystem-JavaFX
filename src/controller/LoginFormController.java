package controller;

import com.mongodb.DB;
import dao.CrudUtil;
import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginFormController {


    public TextField txtEmail;
    public TextField txtType;
    public PasswordField txtPassword;
    public ComboBox cmbSelectType;

//    ObservableList<String> observableList= FXCollections.observableArrayList();


    public void initialize() throws Exception {
//        observableList.add("Admin");
//        observableList.add("Reciption");
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText().trim();

        if (email.length()>0 && password.length()>0){

            if (email.equalsIgnoreCase("a")
                    && password.equalsIgnoreCase("1")){


                URL resource = this.getClass().
                        getResource("/view/DashBoardForm.fxml");
                Parent load = FXMLLoader.load(resource);// ALt + Enter
                Scene scene= new Scene(load);
                Stage stage= new Stage();
                stage.setScene(scene);
                stage.show();


            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again !!!!",
                        ButtonType.OK,ButtonType.NO).show();
            }
        }else{
            new Alert(Alert.AlertType.WARNING,"Empty !!!!",
                    ButtonType.OK,ButtonType.NO).show();
        }

    }

    public void cmbSelectTypeOnAction(ActionEvent actionEvent) {
    }
}
