package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerPerfil implements Initializable {

    @FXML
    private Label nomePerfil;
    @FXML
    private Label emailPerfil;
    @FXML
    private Label lbInfos;
    @FXML
    private Label lbPontos;



    @FXML
    private void voltarPagina(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BancoDeUsuarios bancoDeUsuarios = BancoDeUsuarios.getInstancia();
        Usuario usuario = bancoDeUsuarios.getBancoDeUsuarios().get(Sessao.getIdDoUsuario());
        if(usuario.getTipoUsuario() == TipoDeUsuario.PROFESSOR){
            Professor prof = (Professor) usuario;
            nomePerfil.setText(prof.getNome());
            emailPerfil.setText(prof.getEmail());
            lbInfos.setText(prof.getInfos());
            lbInfos.setVisible(true);
        }
        if (usuario.getTipoUsuario() == TipoDeUsuario.ALUNO){
            Aluno aluno = (Aluno) usuario;
            nomePerfil.setText(aluno.getNome());
            emailPerfil.setText(aluno.getEmail());
            lbPontos.setText(String.valueOf(aluno.getPontuacao()));
            lbPontos.setVisible(true);
        }
    }
}
