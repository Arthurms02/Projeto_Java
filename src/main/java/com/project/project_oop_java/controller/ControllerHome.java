package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCriadorInvalido;
import com.project.project_oop_java.exceptions.ExceptionSalaInvalida;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;

public class ControllerHome implements Initializable {

    @FXML
    private Label lbErro;
    @FXML
    private VBox painelQuestoes;
    @FXML
    private Button btnSala;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BancoDeSalas bancoDeSalas = BancoDeSalas.getInstancia();

        if(bancoDeSalas.totalDeSalas() > 0){
            btnSala.setVisible(true);
        }

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
                    String.valueOf(questao.getKey()),
                    questao.getValue().getIdDoCriador()
            ));
        }
    }

    @FXML
    private void abrirSala(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/sala-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    private void btnPerfil(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/perfil-view.fxml"));
        Parent root = loader.load();
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


    @FXML
    public VBox criarCard(String fonte, String enunciado, String totalPontos, String altA, String altB, String altC, String altD, String altE, String id, String idDoUsuario){

        VBox cardVBox = new VBox();
        cardVBox.setPrefSize(912, 250);
        cardVBox.setMaxSize(912,250);
        cardVBox.setStyle("-fx-background-color: #DDDDDD; -fx-padding: 20px; -fx-border-radius: 10px; -fx-border-style: solid; -fx-background-radius: 10px;");
        cardVBox.setId(idDoUsuario);

        // Topo - Enunciado, Criador, TotalPontos
        AnchorPane headerPane = new AnchorPane();
        headerPane.setPrefSize(900, 90);
        headerPane.setMaxSize(907,94);

        Label lblEnunciado = new Label(enunciado);
        lblEnunciado.setLayoutX(25);
        lblEnunciado.setMaxWidth(800);
        lblEnunciado.setWrapText(true);
        lblEnunciado.setLayoutY(30);
        lblEnunciado.setTextAlignment(TextAlignment.JUSTIFY);

        Label lblCriador = new Label(fonte); // Fonte de onde veio a questão
        lblCriador.setLayoutX(30);
        lblCriador.setLayoutY(5);

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
        btnEditar.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/editarQuestao-view.fxml"));
                Parent root = loader.load();

                // Pegando o controller da tela de edição
                ControllerEditarQuestao controller = loader.getController();

                // Pegando a questão do banco pelo id
                BancoDeQuestoes banco = BancoDeQuestoes.getInstancia();
                Questao questao = banco.getBancoDeQuestoes().get(Integer.parseInt(id));

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

        Button btnAdicionar = new Button("Adicionar a Sala");
        btnAdicionar.setStyle("-fx-background-color: #fcb502");
        btnAdicionar.setTextFill(Color.WHITE);
        btnAdicionar.setLayoutX(250);
        btnAdicionar.setLayoutY(7);
        btnAdicionar.setOnAction(event -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/popUpValidarSala-view.fxml"));
                Parent root = loader.load();

                ControllerPopUp controller = loader.getController();

                Stage stage = new Stage();
                controller.setStage(stage);

                BancoDeSalas bancoSala = BancoDeSalas.getInstancia();
                BancoDeQuestoes bancoQuestoes = BancoDeQuestoes.getInstancia();
                BancoDeQuestoesSala bancoDeQuestoesSala = BancoDeQuestoesSala.getInstancia();

                controller.setIdConfirmado(idDaSala -> {
                    try {
                        if (bancoSala.validarSala(idDaSala)) {
                            if (String.valueOf(Sessao.getIdDoUsuario()).equals(bancoSala.buscarSala(idDaSala).getIdDoCriador())) {
                                Questao questao = bancoQuestoes.getQuestao(Integer.parseInt(id));

                                // mover para o banco de sala
                                bancoDeQuestoesSala.cadastrarQuestoesSala(questao);

                                System.out.println("Questão movida com sucesso!");
                            }else {
                                throw new ExceptionCriadorInvalido("Usuario não é o dono da sala!.");
                            }
                        } else {
                            throw new ExceptionSalaInvalida("Codigo da sala invalido ou não cadastrado!");
                        }
                    } catch (NumberFormatException | ExceptionSalaInvalida | ExceptionCriadorInvalido e) {
                        lbErro.setVisible(true);
                        lbErro.setText(e.getMessage());
                    }
                });

                stage.setScene(new Scene(root));
                stage.setTitle("Mover Questão");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ExceptionSalaInvalida | ExceptionCriadorInvalido e) {
                lbErro.setText(e.getMessage());
                lbErro.setVisible(true);
            }
        });


        if(idDoUsuario.equals(String.valueOf(Sessao.getIdDoUsuario()))){
            botoesPane.getChildren().addAll(btnResponder, btnRemover, btnEditar,btnAdicionar);
        }else{
            botoesPane.getChildren().addAll(btnResponder, btnAdicionar);
        }


        // Adicionando tudo ao VBox principal
        cardVBox.getChildren().addAll(headerPane, alternativasBox, botoesPane);

        return cardVBox;
    }


}