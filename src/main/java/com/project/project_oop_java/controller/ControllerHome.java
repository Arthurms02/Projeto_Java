package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.BancoDeUsuarios;
import com.project.project_oop_java.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerHome {

    @FXML
    private Label textTest;


    @FXML
    private void btnPerfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/perfil-view.fxml"));
        Parent root = loader.load();

        // Recupera o controller da próxima view
        ControllerPerfil perfilController = loader.getController();

        // Passa o nome do usuário
        BancoDeUsuarios b = BancoDeUsuarios.getInstancia();
        Usuario u = b.getBancoDeUsuarios().get(Sessao.getIdDoUsuario());

        perfilController.setNomePerfil(u.getNome());
        perfilController.setEmailPerfil(u.getEmail());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void criarQuestao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/criarQuestao-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnSala(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/criarSala-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void voltarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}