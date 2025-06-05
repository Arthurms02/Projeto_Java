package com.project.project_oop_java.model;


import java.util.HashMap;

public class BancoDeSalas {
    private static BancoDeSalas instancia;
    private HashMap<Integer,Sala> bancoDeSalas;

    private BancoDeSalas(){
        this.bancoDeSalas = new HashMap<>();
    }

    public static BancoDeSalas getInstancia(){
        if(instancia == null){
            return instancia = new BancoDeSalas();
        }
        return instancia;
    }

    public void cadastraSala(int codigo, Sala sala){
        bancoDeSalas.put(codigo,sala);
    }

    public boolean validarSala(int codigo){
        return bancoDeSalas.containsKey(codigo);
    }

    public int totalDeSalas(){
        return bancoDeSalas.size();
    }

    public Sala buscarSala(int codigo){
        return bancoDeSalas.get(codigo);
    }

    public void removerSala(int codigo){
        bancoDeSalas.remove(codigo);
    }

    public HashMap getBancoDeSala(){
        return bancoDeSalas;
    }
}
