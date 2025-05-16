package com.project.project_oop_java.model;

public class Professor extends Usuario{
    private String infos;

    public Professor(String nome, String email, String senha, TipoDeUsuario tipoUsuario) {
        super(nome, email, senha, tipoUsuario);
    }


}
