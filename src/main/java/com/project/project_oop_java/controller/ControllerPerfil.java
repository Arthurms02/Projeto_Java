package com.project.project_oop_java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerPerfil {

    @FXML
    private Label nomePerfil;
    @FXML
    private Label emailPerfil;

    public void setNomePerfil(String nome){
        nomePerfil.setText(nome);
    }

    public void setEmailPerfil(String email){
        emailPerfil.setText(email);
    }

    @FXML
    private void voltarPagina(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
