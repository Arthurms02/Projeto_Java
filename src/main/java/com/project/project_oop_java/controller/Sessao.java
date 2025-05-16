package com.project.project_oop_java.controller;

public class Sessao {
    private static int idDoUsuario;

    public static void setIdDoUsuario(int id){
        idDoUsuario = id; // sempre que autentificar um usuario no login setar o id dele.
    }

    public static int getIdDoUsuario(){
        return idDoUsuario;
    }
}
