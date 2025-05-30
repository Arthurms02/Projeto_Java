package com.project.project_oop_java.model;

public class Professor extends Usuario{
    private String infos;

    public Professor(String nome, String email, String senha, TipoDeUsuario tipoUsuario,String infos) {
        super(nome, email, senha, tipoUsuario);
        this.infos = infos;
    }

    public String getInfos() {
        return infos;
    }

    public void setInfos(String infos) {
        this.infos = infos;
    }
}
