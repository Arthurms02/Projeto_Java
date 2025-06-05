package com.project.project_oop_java;

import com.project.project_oop_java.controller.EscritaArquivos;
import com.project.project_oop_java.controller.LeituraArquivos;
import com.project.project_oop_java.model.BancoDeQuestoes;
import com.project.project_oop_java.model.BancoDeQuestoesSala;
import com.project.project_oop_java.model.BancoDeSalas;
import com.project.project_oop_java.model.BancoDeUsuarios;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LeituraArquivos.lerArquivoSalas("C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Sala.txt");
        LeituraArquivos.lerArquivoUsuarios("C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Usuarios.txt");
        LeituraArquivos.lerArquivoQuestoes("C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Questoes.txt");
        LeituraArquivos.lerArquivoQuestoes("C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\QuestoesSala.txt");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view/login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 550);
        stage.setTitle("Home Task");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Parando a aplicação... Salvando dados...");
        BancoDeSalas bancoDeSalas = BancoDeSalas.getInstancia();
        EscritaArquivos.salvarHashMapDeSalas(bancoDeSalas.getBancoDeSala(),"C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Sala.txt");
        BancoDeUsuarios bancoDeUsuarios = BancoDeUsuarios.getInstancia();
        EscritaArquivos.salvarHashMapDeUsuarios(bancoDeUsuarios.getBancoDeUsuarios(), "C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Usuarios.txt");
        BancoDeQuestoes bancoDeQuestoes = BancoDeQuestoes.getInstancia();
        EscritaArquivos.salvarHashMapDeQuestoes(bancoDeQuestoes.getBancoDeQuestoes(),"C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Questoes.txt");
        BancoDeQuestoesSala bancoDeQuestoesSala = BancoDeQuestoesSala.getInstancia();
        EscritaArquivos.salvarHashMapDeQuestoes(bancoDeQuestoesSala.getBancoDeQuestoesSala(),"C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\QuestoesSala.txt");
        super.stop();
    }

    public static void main(String[] args) {
        launch();
    }
}