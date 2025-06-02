package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LeituraArquivos {
    public static void lerArquivoComoHashMap(String caminho) {


        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            BancoDeSalas bancoDeSalas = BancoDeSalas.getInstancia();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";", 5);// Divide em no máximo 6 partes \\[;\[\]]
                if (partes.length >= 4) {
                    String codigo = partes[0].trim();
                    String idCriador = partes[1].trim();
                    String nomeSala = partes[2].trim();
                    String donoDaSala = partes[3].trim();
                    String valor4 = partes[4].trim();
                    Sala sala = new Sala(idCriador,nomeSala,donoDaSala,Integer.parseInt(codigo));
                    String[] partes2 = valor4.split("/",2);
                    String[] materiais = partes2[0].split(";");
                    if (partes2.length == 2) {
                        for (String material : materiais) {
                            sala.setMaterialApoio(material);
                        }
                        String[] partes3 = partes2[1].split(":");
                        ArrayList<String> participantesSemEspaco= new ArrayList<>();

                        for(String part: partes3){
                            if(!part.trim().isEmpty()){
                                participantesSemEspaco.add(part);
                            }
                        }

                        for(String participante: participantesSemEspaco){
                            String[] dados = participante.split(";");
                            ArrayList<String> dadosSemEspacos= new ArrayList<>();
                            for(String dado: dados){
                                if(!dado.trim().isEmpty()){
                                    dadosSemEspacos.add(dado);
                                }
                            }
                            if (dadosSemEspacos.size() >= 4){
                                String tipo = dadosSemEspacos.get(0);
                                String nome = dadosSemEspacos.get(1);
                                String email = dadosSemEspacos.get(2);
                                String senha = dadosSemEspacos.get(3);
                                switch (tipo){
                                    case "PROFESSOR":
                                        Professor p = new Professor(nome, email,senha, TipoDeUsuario.PROFESSOR,"test");
                                        sala.setParticipantes(p);
                                        break;
                                    case "ALUNO":
                                        Aluno a = new Aluno(nome,email,senha,TipoDeUsuario.ALUNO);
                                        sala.setParticipantes(a);
                                        break;
                                    case"OUTROS":
                                        Outros o = new Outros(nome,email,senha,TipoDeUsuario.OUTROS);
                                        sala.setParticipantes(o);
                                        break;
                                }
                            }
                        }
                    }
                    bancoDeSalas.cadastraSala(Integer.parseInt(codigo),sala);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

    }
}
