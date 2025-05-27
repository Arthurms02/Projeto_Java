package com.project.project_oop_java.controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class ControllerPopUp {

    private Stage stage;
    private Consumer<Integer> idConfirmado;

    @FXML
    private TextField campoCodigo;
    @FXML
    private Label lbErro;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIdConfirmado(Consumer<Integer> callback) {
        this.idConfirmado = callback;
    }

    @FXML
    public void btnEnviar(ActionEvent event){
        try{
            int idSala = Integer.parseInt(campoCodigo.getText().trim());
            if(idConfirmado != null){
                idConfirmado.accept(idSala);
            }
            stage.close();
        }catch (NumberFormatException ex){
            campoCodigo.setStyle("-fx-border-color: red;");
            lbErro.setVisible(true);
            lbErro.setText("Codigo invalido digite somente numeros!");
        }
    }

}
