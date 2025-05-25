package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.Sala;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCriarSala {

    @FXML
    private TextField campoApoio1;
    @FXML
    private TextField campoApoio2;
    @FXML
    private TextField campoApoio3;
    @FXML
    private TextField campoApoio4;
    @FXML
    private TextField campoApoio5;
    @FXML
    private Button btnApoio2;
    @FXML
    private Button btnApoio3;
    @FXML
    private Button btnApoio4;

    @FXML
    private void btnApoio1(ActionEvent event){
        String link = campoApoio1.getText();

        campoApoio2.setVisible(true);
        btnApoio2.setVisible(true);
    }

    @FXML
    private void btnApoio2(){
        campoApoio3.setVisible(true);
        btnApoio3.setVisible(true);
    }

    @FXML
    private void btnApoio3(){
        campoApoio4.setVisible(true);
        btnApoio4.setVisible(true);
    }

    @FXML
    private void btnApoio4(){
        campoApoio5.setVisible(true);
    }


    @FXML
    private void voltarSala(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
