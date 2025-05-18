package com.project.project_oop_java.model;

public class Aluno extends Usuario{
    private int pontuacao;


    public Aluno(String nome, String email, String senha, TipoDeUsuario tipoUsuario) {
        super(nome, email, senha, tipoUsuario);
    }



}
