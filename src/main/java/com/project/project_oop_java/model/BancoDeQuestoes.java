package com.project.project_oop_java.model;

import com.project.project_oop_java.controller.EscritaArquivos;

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
        EscritaArquivos.salvarHashMapDeQuestoes(bancoDeQuestoes,"C:\\Users\\arthur\\Desktop\\Projeto_java\\Projeto_Java\\src\\main\\java\\com\\project\\project_oop_java\\arquivos\\Questoes.txt");
    }

    public  void editarQuestao(String id,Questao novaQuestao){
        bancoDeQuestoes.put(Integer.parseInt(id),novaQuestao);
    }

    public void removerQuestao(String id){
        bancoDeQuestoes.remove(Integer.parseInt(id));
    }

    public HashMap<Integer, Questao> getBancoDeQuestoes() {
        return bancoDeQuestoes;
    }

    public Questao getQuestao(int id){
        return bancoDeQuestoes.get(id);
    }
}
