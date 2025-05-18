package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionSenhaInvalida;
import com.project.project_oop_java.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private Label textTest;
    @FXML
    private VBox painelQuestoes;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BancoDeQuestoes b = BancoDeQuestoes.getInstancia();
        System.out.println(b.getBancoDeQuestoes());
        for (Map.Entry<Integer, Questao> questao : b.getBancoDeQuestoes().entrySet()) {
            painelQuestoes.getChildren().add(criarCard(
                    questao.getValue().getFonte(),
                    questao.getValue().getEnunciado(),
                    questao.getValue().getTotalDePontos(),
                    questao.getValue().getAlternativaA(),
                    questao.getValue().getAlternativaB(),
                    questao.getValue().getAlternativaC(),
                    questao.getValue().getAlternativaD(),
                    questao.getValue().getAlternativaE(),
                    String.valueOf(questao.getKey())
            ));
        }
    }

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
    private void removerCard(){
        System.out.println("Apagado!");
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

    public void setPainelQuestoes(VBox questao) {
        painelQuestoes.getChildren().add(questao);
    }

    @FXML
    public VBox criarCard(String fonte, String enunciado, String totalPontos, String altA, String altB, String altC, String altD, String altE, String id){

        VBox cardVBox = new VBox();
        cardVBox.setPrefSize(912, 250);
        cardVBox.setStyle("-fx-background-color: #DDDDDD; -fx-padding: 20px; -fx-border-radius: 10px; -fx-border-style: solid; -fx-background-radius: 10px;");

        // Topo - Enunciado, Criador, TotalPontos
        AnchorPane headerPane = new AnchorPane();
        headerPane.setPrefSize(907, 94);

        Label lblEnunciado = new Label(enunciado);
        lblEnunciado.setLayoutX(48);
        lblEnunciado.setLayoutY(17);

        Label lblCriador = new Label(fonte); // Fonte de onde veio a questão
        lblCriador.setLayoutX(10);
        lblCriador.setLayoutY(50);

        Label lblTotalPontos = new Label(totalPontos);
        lblTotalPontos.setLayoutX(806);
        lblTotalPontos.setLayoutY(6);

        headerPane.getChildren().addAll(lblEnunciado, lblCriador, lblTotalPontos);

        // Alternativas
        VBox alternativasBox = new VBox(2);
        alternativasBox.setPrefSize(871, 102);

        String[] alternativas = {altA, altB, altC, altD, altE};
        for (String alt : alternativas) {
            Label lbl = new Label(alt);
            lbl.setFont(Font.font("Inter Regular", 12));
            alternativasBox.getChildren().add(lbl);
        }

        // Rodapé com botões
        AnchorPane botoesPane = new AnchorPane();
        botoesPane.setPrefSize(870, 50);

        Button btnResponder = new Button("Responder");
        btnResponder.setStyle("-fx-background-color: green;");
        btnResponder.setTextFill(Color.WHITE);
        btnResponder.setLayoutX(14);
        btnResponder.setLayoutY(7);

        Button btnRemover = new Button("Remover");
        btnRemover.setStyle("-fx-background-color: red;");
        btnRemover.setTextFill(Color.WHITE);
        btnRemover.setLayoutX(100);
        btnRemover.setLayoutY(7);
        btnRemover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                painelQuestoes.getChildren().remove(cardVBox);
                BancoDeQuestoes b = BancoDeQuestoes.getInstancia();
                b.removerQuestao(id);
                System.out.println("Removido card com id: " + id);
            }
        });

        Button btnEditar = new Button("Editar");
        btnEditar.setStyle("-fx-background-color: blue;");
        btnEditar.setTextFill(Color.WHITE);
        btnEditar.setLayoutX(176);
        btnEditar.setLayoutY(7);

        botoesPane.getChildren().addAll(btnResponder, btnRemover, btnEditar);

        cardVBox.setId(id);

        // Adicionando tudo ao VBox principal
        cardVBox.getChildren().addAll(headerPane, alternativasBox, botoesPane);

        return cardVBox;
    }


}