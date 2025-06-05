package com.project.project_oop_java.controller;

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

public class ControllerSala implements Initializable {

    private static int codigoSala;

    @FXML
    private VBox painelQuestoesSala;
    @FXML
    private Label nomeSala;
    @FXML
    private Label nomeCriador;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BancoDeQuestoesSala bancoDeQuestoesSala = BancoDeQuestoesSala.getInstancia();
        System.out.println("Adicionado na sala" + bancoDeQuestoesSala.getBancoDeQuestoesSala());
        for (Map.Entry<Integer, Questao> questao : bancoDeQuestoesSala.getBancoDeQuestoesSala().entrySet()) {
            painelQuestoesSala.getChildren().add(criarCard(
                    questao.getValue().getFonte(),
                    questao.getValue().getEnunciado(),
                    questao.getValue().getTotalDePontos(),
                    questao.getValue().getAlternativaA(),
                    questao.getValue().getAlternativaB(),
                    questao.getValue().getAlternativaC(),
                    questao.getValue().getAlternativaD(),
                    questao.getValue().getAlternativaE(),
                    String.valueOf(questao.getKey()),
                    questao.getValue().getIdDoCriador()
            ));
        }
        BancoDeSalas bancoDeSalas = BancoDeSalas.getInstancia();
        for (Object s : bancoDeSalas.getBancoDeSala().values()){
            Sala sala = (Sala) s;
            nomeSala.setText(sala.getNomeDaSala());
            nomeCriador.setText(sala.getDonoDaSala());
        }


    }
    @FXML
    public void adicionarQuestoes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void setCodigoSala(int codigoSala) {
        ControllerSala.codigoSala = codigoSala;
    }

    @FXML
    public VBox criarCard(String fonte, String enunciado, String totalPontos, String altA, String altB, String altC, String altD, String altE, String id, String idDoUsuario){

        VBox cardVBox = new VBox();
        cardVBox.setPrefSize(912, 250);
        cardVBox.setStyle("-fx-background-color: #DDDDDD; -fx-padding: 20px; -fx-border-radius: 10px; -fx-border-style: solid; -fx-background-radius: 10px;");
        cardVBox.setId(idDoUsuario);

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
        btnResponder.setOnAction(e -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/responderQuestao-view.fxml"));
                Parent root = loader.load();

                // pega o controller e envia a questão
                ControllerResponderQuestao controller = loader.getController();
                BancoDeQuestoes banco = BancoDeQuestoes.getInstancia();
                Questao questao = banco.getBancoDeQuestoes().get(Integer.parseInt(id));
                controller.carregarQuestao(questao); // passe o objeto Questao

                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });


        Button btnRemover = new Button("Remover");
        btnRemover.setStyle("-fx-background-color: red;");
        btnRemover.setTextFill(Color.WHITE);
        btnRemover.setLayoutX(100);
        btnRemover.setLayoutY(7);
        btnRemover.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                painelQuestoesSala.getChildren().remove(cardVBox);
                BancoDeQuestoesSala b = BancoDeQuestoesSala.getInstancia();
                b.removerQuestaoSala(id);
                System.out.println("Removido card com id: " + id);
            }
        });

        Button btnEditar = new Button("Editar");
        btnEditar.setStyle("-fx-background-color: blue;");
        btnEditar.setTextFill(Color.WHITE);
        btnEditar.setLayoutX(176);
        btnEditar.setLayoutY(7);
        btnEditar.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/editarQuestao-view.fxml"));
                Parent root = loader.load();

                // Pegando o controller da tela de edição
                ControllerEditarQuestao controller = loader.getController();

                // Pegando a questão do banco pelo id
                BancoDeQuestoesSala banco = BancoDeQuestoesSala.getInstancia();
                Questao questao = banco.getBancoDeQuestoesSala().get(Integer.parseInt(id));

                // Envia a questão para preencher os campos
                controller.setQuestaoEditada(id,questao);

                // Troca a cena
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });



        if(idDoUsuario.equals(String.valueOf(Sessao.getIdDoUsuario()))){
            botoesPane.getChildren().addAll(btnResponder, btnRemover, btnEditar);
        }else{
            botoesPane.getChildren().addAll(btnResponder);
        }


        // Adicionando tudo ao VBox principal
        cardVBox.getChildren().addAll(headerPane, alternativasBox, botoesPane);

        return cardVBox;
    }
}
