package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
import com.project.project_oop_java.exceptions.ExceptionNumeroDePontosNegativos;
import com.project.project_oop_java.exceptions.ExceptionRespostaInvalida;
import com.project.project_oop_java.model.BancoDeQuestoes;
import com.project.project_oop_java.model.Questao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerEditarQuestao {

    @FXML
    private TextField editarFonte;
    @FXML
    private TextField editarTotalPontos;
    @FXML
    private TextArea editarEnunciado;
    @FXML
    private TextField editarAlternativaA;
    @FXML
    private TextField editarAlternativaB;
    @FXML
    private TextField editarAlternativaC;
    @FXML
    private TextField editarAlternativaD;
    @FXML
    private TextField editarAlternativaE;
    @FXML
    private  TextField editarRespostaCorreta;
    @FXML
    private Label lbEditErro;

    private Questao questaoEditada;

    private String idDaQuestao;


    public void setQuestaoEditada(String id ,Questao questao) {
        this.questaoEditada = questao;
        this.idDaQuestao = id;


        editarFonte.setText(questao.getFonte());
        editarTotalPontos.setText(questao.getTotalDePontos());
        editarEnunciado.setText(questao.getEnunciado());
        editarAlternativaA.setText(questao.getAlternativaA());
        editarAlternativaB.setText(questao.getAlternativaB());
        editarAlternativaC.setText(questao.getAlternativaC());
        editarAlternativaD.setText(questao.getAlternativaD());
        editarAlternativaE.setText(questao.getAlternativaE());
        editarRespostaCorreta.setText(questao.getRespostaCorreta());

    }

    @FXML
    private void editarQuestao(ActionEvent event){

        questaoEditada.setFonte(editarFonte.getText());
        questaoEditada.setTotalDePontos(editarTotalPontos.getText());
        questaoEditada.setEnunciado(editarEnunciado.getText());
        questaoEditada.setAlternativaA(editarAlternativaA.getText());
        questaoEditada.setAlternativaB(editarAlternativaB.getText());
        questaoEditada.setAlternativaC(editarAlternativaC.getText());
        questaoEditada.setAlternativaD(editarAlternativaD.getText());
        questaoEditada.setAlternativaE(editarAlternativaE.getText());
        try {
            // campos
            String fonte = editarFonte.getText();
            String enun = editarEnunciado.getText();
            String pontos = editarTotalPontos.getText();
            String a = editarAlternativaA.getText();
            String b = editarAlternativaB.getText();
            String c = editarAlternativaC.getText();
            String d = editarAlternativaD.getText();
            String e = editarAlternativaE.getText();
            String respCorreta = editarRespostaCorreta.getText();

            // Validar campos nulos e vazios.

            if (fonte == null || fonte.isBlank()){
                throw new ExceptionCampoVazio("Peencha a fonte da questão");
            }
            if (enun == null || enun.isBlank()){
                throw new ExceptionCampoVazio("Digite um enunciado para sua questão");
            }
            if (pontos == null || pontos.isBlank()){
                throw new ExceptionCampoVazio("Digite o total de pontos ou use 0");
            }
            if (a == null || a.isBlank()){
                throw new ExceptionCampoVazio("Digite a alternativa A da questão");
            }
            if (b == null || b.isBlank()){
                throw new ExceptionCampoVazio("Digite a alternativa B da questão ");
            }
            if (c == null || c.isBlank()){
                throw new ExceptionCampoVazio("Digite a alternativa C da questão");
            }
            if (d == null || d.isBlank()){
                throw new ExceptionCampoVazio("Digite a alternativa  D da questão");
            }
            if (e == null || e.isBlank()){
                throw new ExceptionCampoVazio("Digite a alternativa E da questão ou use NDA");
            }
            if (respCorreta == null || respCorreta.isBlank()){
                throw new ExceptionCampoVazio("Digite a resposta para essa questão");
            }

            // validar campo da resposta
            String[] alternativasValidas = {"A","B","C","D","E"};
            boolean encontrado = false;

            for (String alterntiva : alternativasValidas){
                if (respCorreta.equalsIgnoreCase(alterntiva)){
                    encontrado = true;
                    break;
                }
            }

            if(!encontrado){
                throw new ExceptionRespostaInvalida("Resposta invalida tente: A | B | C | D | E");
            }

            // validar pontos

            if(Double.parseDouble(pontos) < 0 ){
                throw new ExceptionNumeroDePontosNegativos("Não é permitido pontos negativos!");
            }

            // salvar no banco

            BancoDeQuestoes banco = BancoDeQuestoes.getInstancia();
            banco.editarQuestao(idDaQuestao,questaoEditada);

            // carrega home

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.show();

        } catch (NumberFormatException ex) {
            lbEditErro.setText("Total de pontos inválido! Digite um número válido.");
            lbEditErro.setVisible(true);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ExceptionCampoVazio ex){
            lbEditErro.setText(ex.getMessage());
            lbEditErro.setVisible(true);
        } catch (ExceptionNumeroDePontosNegativos ex){
            lbEditErro.setText(ex.getMessage());
            lbEditErro.setVisible(true);
        } catch (ExceptionRespostaInvalida ex ){
            lbEditErro.setText(ex.getMessage());
            lbEditErro.setVisible(true);
        }

    }

    @FXML
    private void voltarHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
