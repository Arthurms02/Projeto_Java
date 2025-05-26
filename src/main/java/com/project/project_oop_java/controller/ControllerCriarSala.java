package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
import com.project.project_oop_java.exceptions.ExceptionLinkInvalido;
import com.project.project_oop_java.model.BancoDeSalas;
import com.project.project_oop_java.model.Sala;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerCriarSala {
    @FXML
    private TextField campoNomeSala;
    @FXML
    private TextField campoCriadorSala;
    @FXML
    private TextField campoCodigoSala;
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
    private Label lbErros;

    @FXML
    private void btnApoio1(ActionEvent event){
        campoApoio2.setVisible(true);
        btnApoio2.setVisible(true);
    }

    @FXML
    private void btnApoio2(ActionEvent event){
        campoApoio3.setVisible(true);
        btnApoio3.setVisible(true);
    }

    @FXML
    private void btnApoio3(ActionEvent event){
        campoApoio4.setVisible(true);
        btnApoio4.setVisible(true);
    }

    @FXML
    private void btnApoio4(ActionEvent event){
        campoApoio5.setVisible(true);
    }

    @FXML
    public void criarSala(ActionEvent event){
        try {
            String nomeSala = campoNomeSala.getText();
            String nomeDono = campoCriadorSala.getText();
            int codigo = Integer.parseInt(campoCodigoSala.getText());
            String materialApoio1 = campoApoio1.getText();
            String materialApoio2 = campoApoio2.getText();
            String materialApoio3 = campoApoio3.getText();
            String materialApoio4 = campoApoio4.getText();
            String materialApoio5 = campoApoio5.getText();

            //Validar campos
            if (nomeSala == null || nomeSala.isBlank()){
                throw new ExceptionCampoVazio("Campo de nome nao pode ser vazio!");
            }
            if (nomeDono == null || nomeDono.isBlank()){
                throw new ExceptionCampoVazio("Campo de dono da sala não pode ser vazio!");
            }
            // abri a sala
            Sala sala = new Sala(nomeSala,nomeDono,codigo);

            // Validar LINKS e adicionar na sala

            String[] materiais = {materialApoio1,materialApoio2,materialApoio3,materialApoio4,materialApoio5};

            for (String material : materiais){
                if (!material.trim().isEmpty()){
                    sala.validarMaterial(material);
                    sala.setMaterialApoio(material);
                }

            }
            // adicionando no banco
            BancoDeSalas banco = BancoDeSalas.getInstancia();
            banco.cadastraSala(codigo, sala);
            System.out.println(banco.getBancoDeSala());


        } catch (ExceptionCampoVazio | ExceptionLinkInvalido e) {
            lbErros.setText(e.getMessage());
            lbErros.setVisible(true);
        } catch (NumberFormatException ex){
            lbErros.setText("Só são permitido numeros no codigo da sala!");
            lbErros.setVisible(true);
        }
    }


    @FXML
    private void voltarSala(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
