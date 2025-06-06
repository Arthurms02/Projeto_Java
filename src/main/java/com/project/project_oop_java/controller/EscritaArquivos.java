package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.Questao;
import com.project.project_oop_java.model.Sala;
import com.project.project_oop_java.model.Usuario;


import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EscritaArquivos {

    public static void salvarHashMapDeSalas(HashMap<Integer, Sala> salas, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo,true)) {
            for (Map.Entry<Integer, Sala> entrada : salas.entrySet()) {
                if (!LeituraArquivos.getIdDaSala().contains(entrada.getKey())) {
                    String linha = entrada.getKey() + ";" + entrada.getValue().formatarString();
                    writer.write(linha + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar salas: " + e.getMessage());
        }
    }

    public static void salvarHashMapDeQuestoes(HashMap<Integer, Questao> questao, String caminhoArquivo) {
            try (FileWriter writer = new FileWriter(caminhoArquivo,true)) {
                for (Map.Entry<Integer, Questao> entrada : questao.entrySet()) {
                    if (!LeituraArquivos.getIdCadastrado().contains(entrada.getKey())) {
                        String linha = entrada.getKey() + ";" + entrada.getValue().formatarString();
                        writer.write(linha + "\n");
                    }

                }
            } catch (IOException e) {
                System.out.println("Erro ao salvar salas: " + e.getMessage());
            }
    }

    public static void salvarHashMapDeUsuarios(HashMap<Integer, Usuario> usuario, String caminhoArquivo) {
        try (FileWriter writer = new FileWriter(caminhoArquivo,true)) {
            for (Map.Entry<Integer, Usuario> entrada : usuario.entrySet()) {
                if (!LeituraArquivos.getIdDoUsuario().contains(entrada.getKey())) {
                    String linha = entrada.getKey() + ";" + entrada.getValue().formatarString();
                    writer.write(linha + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar salas: " + e.getMessage());
        }
    }

    public static void salvarHashMapDeQuestoesSala(HashMap<Integer, Questao> questao, String caminhoArquivo){
        try (FileWriter writer = new FileWriter(caminhoArquivo,true)) {
            for (Map.Entry<Integer, Questao> entrada : questao.entrySet()) {
                if (!LeituraArquivos.getIdQuestaoCadastradoSala().contains(entrada.getKey())){
                    String linha = entrada.getKey() + ";" + entrada.getValue().formatarString();
                    writer.write(linha + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar salas: " + e.getMessage());
        }
    }

}
