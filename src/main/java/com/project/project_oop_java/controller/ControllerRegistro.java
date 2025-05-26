package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
import com.project.project_oop_java.exceptions.ExceptionEmailInvalido;
import com.project.project_oop_java.exceptions.ExceptionEmailJaCadastrado;
import com.project.project_oop_java.model.BancoDeUsuarios;
import com.project.project_oop_java.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerRegistro {

    @FXML
    private Label labelTestResg;
    @FXML
    private TextField campoNomeRegistro;
    @FXML
    private TextField campoEmailRegistro;
    @FXML
    private TextField campoConfiEmailRegistro;
    @FXML
    private TextField campoSenhaRegistro;
    @FXML
    private ChoiceBox<String> campoBoxRegistro;


    @FXML
    private void cadastrar(ActionEvent event) {
        String nome = campoNomeRegistro.getText();
        String email = campoEmailRegistro.getText();
        String confEmail = campoConfiEmailRegistro.getText();
        String senha = campoSenhaRegistro.getText();
        String tipo = campoBoxRegistro.getValue();

        try {

            //Validando campos vazios e nulos

            if (nome == null || nome.isBlank()){
                throw new ExceptionCampoVazio("Campo vazio preencha todos os campos!");
            }
            if (email == null || email.isBlank()){
                throw new ExceptionCampoVazio("Campo vazio preencha todos os campos!");
            }
            if (confEmail == null || confEmail.isBlank()){
                throw new ExceptionCampoVazio("Campo vazio preencha todos os campos!");
            }
            if (senha == null || senha.isBlank()){
                throw new ExceptionCampoVazio("Campo vazio preencha todos os campos!");
            }
            if (tipo == null || tipo.isBlank()){
                throw new ExceptionCampoVazio("Campo vazio preencha todos os campos!");
            }
            // Validar email
            Usuario.validarEmail(email.trim());

            // Validar se o usuario já esta no banco de usuario.
            Usuario.validarEmailJaCadastrado(email.trim());

            //Validando os campos email e confirmar email

            if(!Usuario.confirmaEmailRegistro(email.trim(),confEmail.trim())){
                throw new ExceptionEmailInvalido("Verifique se os email são iguais!");
            }
            //Criando usuario
            BancoDeUsuarios b = BancoDeUsuarios.getInstancia();
            b.cadastrarNoBanco(Usuario.cadastraUsuario(nome.trim(),email.trim(),senha.trim(),tipo.trim()));

            System.out.println(b.getBancoDeUsuarios());

            Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/login-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ExceptionCampoVazio | ExceptionEmailJaCadastrado | ExceptionEmailInvalido e){
            labelTestResg.setText(e.getMessage());
            labelTestResg.setVisible(true);
        } catch (NullPointerException e){
            labelTestResg.setText("Cadastro não realizado!!");
            labelTestResg.setVisible(true);
        }

    }

    @FXML
    private void redirecionarLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/login-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
