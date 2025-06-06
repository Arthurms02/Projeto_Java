package com.project.project_oop_java.model;

import java.util.HashMap;

public class BancoDeQuestoesSala {
    private static BancoDeQuestoesSala instancia;
    private static int id;
    private HashMap<Integer,Questao> bancoDeQuestoesSala;

    private BancoDeQuestoesSala() {
        this.bancoDeQuestoesSala = new HashMap<>();
    }

    public static BancoDeQuestoesSala getInstancia() {
        if (instancia == null) {
            return instancia = new BancoDeQuestoesSala();
        }
        return instancia;
    }

    public void cadastrarQuestoesSala(Questao questao){
        id++;
        bancoDeQuestoesSala.put(id,questao);
    }

    public void escreverQuestao(int idQuestao , Questao questao){
        id++;
        bancoDeQuestoesSala.put(idQuestao,questao);
    }

    public  void editarQuestaoSala(String id,Questao novaQuestao){
        bancoDeQuestoesSala.put(Integer.parseInt(id),novaQuestao);
    }

    public void removerQuestaoSala(String id){
        bancoDeQuestoesSala.remove(Integer.parseInt(id));
    }

    public HashMap<Integer, Questao> getBancoDeQuestoesSala() {
        return bancoDeQuestoesSala;
    }

    public Questao getQuestao(int id){
        return bancoDeQuestoesSala.get(id);
    }
}
