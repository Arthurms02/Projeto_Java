package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
import com.project.project_oop_java.exceptions.ExceptionEmailInvalido;
import com.project.project_oop_java.exceptions.ExceptionSenhaInvalida;
import com.project.project_oop_java.model.BancoDeUsuarios;
import com.project.project_oop_java.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerLogin {

    @FXML
    private TextField campoEmail;
    @FXML
    private PasswordField campoSenha;
    @FXML
    private Label labelTest;

    @FXML
    private void logar(ActionEvent event) {
        String email = campoEmail.getText();
        String senha = campoSenha.getText();
        try {
            //Validar campos vazio e em branco
            if(email==null || email.isBlank()){
                throw new ExceptionCampoVazio("Peencha todos os campos!");
            }
            if(senha==null || senha.isBlank()){
                throw new ExceptionCampoVazio("Peencha todos os campos!");
            }

            BancoDeUsuarios b = BancoDeUsuarios.getInstancia();
            Usuario.autenticarUsuario(email,senha);
            System.out.println(Sessao.getIdDoUsuario());
            System.out.println(b.getBancoDeUsuarios());// ideia para registro

            Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExceptionEmailInvalido e){
            labelTest.setText(e.getMessage());
            labelTest.setVisible(true);
        } catch (ExceptionSenhaInvalida e){
            labelTest.setText(e.getMessage());
            labelTest.setVisible(true);
        } catch (ExceptionCampoVazio e){
            labelTest.setText(e.getMessage());
            labelTest.setVisible(true);
        }
    }

    @FXML
    private void redirecionarCadastro(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/registro-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
