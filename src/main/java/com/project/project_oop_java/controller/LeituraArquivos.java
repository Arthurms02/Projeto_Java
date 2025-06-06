package com.project.project_oop_java.controller;

import com.project.project_oop_java.model.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class LeituraArquivos {
    private static HashSet<Integer> idCadastrado = new HashSet<>();
    private static HashSet<Integer> idQuestaoCadastradoSala = new HashSet<>();
    private static HashSet<Integer> idDaSala = new HashSet<>();
    private static HashSet<Integer> idDoUsuario = new HashSet<>();


    public static void lerArquivoSalas(String caminho) {


        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            BancoDeSalas bancoDeSalas = BancoDeSalas.getInstancia();

            while ((linha = br.readLine()) != null) {
                String[] partes = linha.split(";", 5);// Divide em no máximo 5 partes
                if (partes.length >= 4) {
                    String codigo = partes[0].trim();
                    String idCriador = partes[1].trim();
                    String nomeSala = partes[2].trim();
                    String donoDaSala = partes[3].trim();
                    String valor4 = partes[4].trim();
                    Sala sala = new Sala(idCriador,nomeSala,donoDaSala,Integer.parseInt(codigo));
                    idDaSala.add(Integer.parseInt(codigo));
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

    public static void lerArquivoUsuarios(String caminho){
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha;
            BancoDeUsuarios bancoDeUsuarios = BancoDeUsuarios.getInstancia();
            while ((linha = br.readLine()) != null){
                String[] partes = linha.split(";",5);
                if (partes.length >= 4){
                    int id = Integer.parseInt(partes[0]);
                    String tipo = partes[1];
                    String nome = partes[2];
                    String email = partes[3];
                    String senha = partes[4];
                    switch (tipo){
                        case "PROFESSOR":
                            Professor p = new Professor(nome, email,senha, TipoDeUsuario.PROFESSOR,"test");
                            bancoDeUsuarios.escreverNoBanco(id,p);
                            idDoUsuario.add(id);
                            break;
                        case "ALUNO":
                            Aluno a = new Aluno(nome,email,senha,TipoDeUsuario.ALUNO);
                            bancoDeUsuarios.escreverNoBanco(id,a);
                            idDoUsuario.add(id);
                            break;
                        case"OUTROS":
                            Outros o = new Outros(nome,email,senha,TipoDeUsuario.OUTROS);
                            bancoDeUsuarios.escreverNoBanco(id,o);
                            idDoUsuario.add(id);
                            break;
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    public static void lerArquivoQuestoes(String caminho){
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha;
            BancoDeQuestoes bancoDeQuestoes = BancoDeQuestoes.getInstancia();
            while ((linha = br.readLine()) != null){
                String[] partes = linha.split(";", 11);
                if(partes.length >= 10){
                    int id = Integer.parseInt(partes[0]);
                    String idDoCriador = partes[1];
                    String fonte = partes[2];
                    String enunciado = partes[3];
                    String totalPontos = partes[4];
                    String altA = partes[5];
                    String altB = partes[6];
                    String altC = partes[7];
                    String altD = partes[8];
                    String altE = partes[9];
                    String respCorreta = partes[10];
                    Questao questao = new Questao(idDoCriador,fonte,enunciado,totalPontos,altA,altB,altC,altD,altE,respCorreta);
                    idCadastrado.add(id);
                    bancoDeQuestoes.escreverQuestao(id,questao);
                }
            }
        }catch (IOException e){
            System.out.println("Erro na leitura do arquivo :" + e.getMessage());
        }
    }

    public static void lerArquivoQuestoesSala(String caminho){
        try(BufferedReader br = new BufferedReader(new FileReader(caminho))){
            String linha;
            BancoDeQuestoesSala bancoDeQuestoesSala = BancoDeQuestoesSala.getInstancia();
            while ((linha = br.readLine()) != null){
                String[] partes = linha.split(";", 11);
                if(partes.length >= 10){
                    int id = Integer.parseInt(partes[0]);
                    String idDoCriador = partes[1];
                    String fonte = partes[2];
                    String enunciado = partes[3];
                    String totalPontos = partes[4];
                    String altA = partes[5];
                    String altB = partes[6];
                    String altC = partes[7];
                    String altD = partes[8];
                    String altE = partes[9];
                    String respCorreta = partes[10];
                    Questao questao = new Questao(idDoCriador,fonte,enunciado,totalPontos,altA,altB,altC,altD,altE,respCorreta);
                    idQuestaoCadastradoSala.add(id);
                    bancoDeQuestoesSala.escreverQuestao(id,questao);
                }
            }
        }catch (IOException e){
            System.out.println("Erro na leitura do arquivo :" + e.getMessage());
        }
    }

    public static void setIdCadastrado(HashSet<Integer> idCadastrado) {
        LeituraArquivos.idCadastrado = idCadastrado;
    }

    public static HashSet<Integer> getIdCadastrado() {
        return idCadastrado;
    }

    public static HashSet<Integer> getIdQuestaoCadastradoSala() {
        return idQuestaoCadastradoSala;
    }

    public static void setIdQuestaoCadastradoSala(HashSet<Integer> idQuestaoCadastradoSala) {
        LeituraArquivos.idQuestaoCadastradoSala = idQuestaoCadastradoSala;
    }

    public static HashSet<Integer> getIdDaSala() {
        return idDaSala;
    }

    public static void setIdDaSala(HashSet<Integer> idDaSala) {
        LeituraArquivos.idDaSala = idDaSala;
    }

    public static HashSet<Integer> getIdDoUsuario() {
        return idDoUsuario;
    }

    public static void setIdDoUsuario(HashSet<Integer> idDoUsuario) {
        LeituraArquivos.idDoUsuario = idDoUsuario;
    }

}
