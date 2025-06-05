package com.project.project_oop_java.model;

import java.util.HashMap;

public class BancoDeUsuarios {
    private static BancoDeUsuarios instancia;
    private static int id;
    private  HashMap<Integer,Usuario> bancoDeUsuarios;

    private BancoDeUsuarios(){
        this.bancoDeUsuarios = new HashMap<>();
    }

    public static BancoDeUsuarios getInstancia() {
        if (instancia == null) {
            instancia = new BancoDeUsuarios();
        }
        return instancia;
    }
    public void cadastrarNoBanco(Usuario user){
        id++;
        bancoDeUsuarios.put(id,user);
    }

    public void buscarUsuario(int id){
        bancoDeUsuarios.containsKey(id);
    }

    public void escreverNoBanco(int id, Usuario usuario){
        bancoDeUsuarios.put(id,usuario);
    }

    public HashMap<Integer, Usuario> getBancoDeUsuarios() {
        return bancoDeUsuarios;
    }

}
