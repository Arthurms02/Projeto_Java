package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.Aluno;
import com.project.project_oop_java.model.BancoDeUsuarios;
import com.project.project_oop_java.model.Questao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerResponderQuestao implements Initializable {

    @FXML
    private Label lbNomeSala;
    @FXML
    private Label lbNomeDono;
    @FXML
    private Label lbFonte;
    @FXML
    private Label lbTotalPontos;
    @FXML
    private Label lbEnunciado;
    @FXML
    private RadioButton radioA;
    @FXML
    private RadioButton radioB;
    @FXML
    private RadioButton radioC;
    @FXML
    private RadioButton radioD;
    @FXML
    private RadioButton radioE;
    @FXML
    private Label lbResposta;
    @FXML
    private ToggleGroup grupoAlternativas;

    private Questao questao;



    public void initialize(URL url, ResourceBundle rb) {
        grupoAlternativas = new ToggleGroup();

        radioA.setToggleGroup(grupoAlternativas);
        radioB.setToggleGroup(grupoAlternativas);
        radioC.setToggleGroup(grupoAlternativas);
        radioD.setToggleGroup(grupoAlternativas);
        radioE.setToggleGroup(grupoAlternativas);
    }

    @FXML
    public void carregarQuestao(Questao questao){
        lbFonte.setText(questao.getFonte());
        lbTotalPontos.setText(questao.getTotalDePontos());
        lbEnunciado.setText(questao.getEnunciado());
        radioA.setText("A-" + questao.getAlternativaA());
        radioB.setText("B-" + questao.getAlternativaB());
        radioC.setText("C-" + questao.getAlternativaC());
        radioD.setText("D-" + questao.getAlternativaD());
        radioE.setText("E-" + questao.getAlternativaE());
        this.questao = questao;
    }

    @FXML
    public void btnResposta(ActionEvent event){
        RadioButton selecionado = (RadioButton) grupoAlternativas.getSelectedToggle();

        String res = selecionado.getText();
        char resposta = res.charAt(0);
        res = String.valueOf(resposta);

        if(selecionado != null){
            if (questao.getRespostaCorreta().equalsIgnoreCase(res)) {
                lbResposta.setText("Resposta Correta!");
                lbResposta.setVisible(true);
                lbResposta.setTextFill(Color.GREEN);
                BancoDeUsuarios bancoDeUsuarios = BancoDeUsuarios.getInstancia();
                Aluno aluno = (Aluno) bancoDeUsuarios.getBancoDeUsuarios().get(Sessao.getIdDoUsuario());
                int pontos = aluno.getPontuacao();
                aluno.setPontuacao(pontos + Integer.parseInt(questao.getTotalDePontos()));
            }else {
                lbResposta.setText("Resposta Incorreta! ");
                lbResposta.setVisible(true);
                lbResposta.setTextFill(Color.RED);
            }
        }else{
            lbResposta.setText("Resposta invalida escolha uma das alternativas!");
            lbResposta.setVisible(true);
            lbResposta.setTextFill(Color.RED);
        }
    }

    @FXML
    public void btnHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
