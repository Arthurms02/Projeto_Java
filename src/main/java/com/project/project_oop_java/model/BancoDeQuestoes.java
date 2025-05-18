package com.project.project_oop_java.model;

import java.util.HashMap;

public class BancoDeQuestoes {
    private static BancoDeQuestoes instancia;
    private static int id;
    private HashMap<Integer,Questao> bancoDeQuestoes;

    private BancoDeQuestoes() {
        this.bancoDeQuestoes = new HashMap<>();
    }

    public static BancoDeQuestoes getInstancia() {
        if (instancia == null) {
            return instancia = new BancoDeQuestoes();
        }
        return instancia;
    }

    public void cadastrarQuestoes(Questao questao){
        id++;
        bancoDeQuestoes.put(id,questao);
    }

    public void removerQuestao(String id){
        bancoDeQuestoes.remove(Integer.parseInt(id));
    }

    public HashMap<Integer, Questao> getBancoDeQuestoes() {
        return bancoDeQuestoes;
    }
}
