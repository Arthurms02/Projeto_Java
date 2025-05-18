package com.project.project_oop_java.controller;

import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
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

public class ControllerQuestao {

    @FXML
    private TextField fonteQuestao;
    @FXML
    private TextField totalPontos;
    @FXML
    private TextArea enunciadoQuestao;
    @FXML
    private TextField alternativaA;
    @FXML
    private TextField alternativaB;
    @FXML
    private TextField alternativaC;
    @FXML
    private TextField alternativaD;
    @FXML
    private TextField alternativaE;
    @FXML
    private Label lbErro;

    @FXML
    private void criarQuestao(ActionEvent event) {
        try {
            String fonte = fonteQuestao.getText();
            String enun = enunciadoQuestao.getText();
            String pontos = totalPontos.getText();
            String a = alternativaA.getText();
            String b = alternativaB.getText();
            String c = alternativaC.getText();
            String d = alternativaD.getText();
            String e = alternativaE.getText();

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

            // adicionar no banco
            BancoDeQuestoes banco = BancoDeQuestoes.getInstancia();
            System.out.println(banco.getBancoDeQuestoes());
            Questao questao = new Questao(fonte, enun, pontos, a, b, c, d, e);
            banco.cadastrarQuestoes(questao);


            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (NumberFormatException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } catch (ExceptionCampoVazio e){
            lbErro.setText(e.getMessage());
            lbErro.setVisible(true);
        }

    }

    @FXML
    private void voltarQuestao(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/com/project/project_oop_java/view/home-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
