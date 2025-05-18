package com.project.project_oop_java.model;

import com.project.project_oop_java.controller.Sessao;
import com.project.project_oop_java.exceptions.ExceptionEmailInvalido;
import com.project.project_oop_java.exceptions.ExceptionEmailJaCadastrado;
import com.project.project_oop_java.exceptions.ExceptionSenhaInvalida;
import com.project.project_oop_java.exceptions.ExceptionUsuarioNaoCadastrado;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public abstract class Usuario {
    private String nome;
    private String email;
    private String senha;
    private TipoDeUsuario tipoUsuario;

    public Usuario(String nome, String email, String senha, TipoDeUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

    public static Usuario cadastraUsuario(String nome, String email, String senha, String tipo ){
        switch (tipo){
            case "Professor":
                Professor p = new Professor(nome,email,senha,TipoDeUsuario.PROFESSOR);
                System.out.println("Professor foi cadastrado");
                return p;
            case "Aluno":
                Aluno a = new Aluno(nome,email,senha,TipoDeUsuario.ALUNO);
                System.out.println("Aluno cadastrado");
                return a;
            case "Outros":
                Outros o = new Outros(nome,email,senha,TipoDeUsuario.OUTROS);
                System.out.println("Outros test!");
                return o;
        }
        return null;
    }

    public static boolean confirmaEmailRegistro(String email, String confirmacaoEmail){
        if (email.equals(confirmacaoEmail)){
            return true;
        }
        return false;
    }

    public static void validarEmail(String email) throws ExceptionEmailInvalido {
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new ExceptionEmailInvalido("Email invalido! Tente Exemple@exemple.com");
        }
    }

    public static void validarEmailJaCadastrado(String email) throws ExceptionEmailJaCadastrado {
        BancoDeUsuarios b = BancoDeUsuarios.getInstancia();
        for(Usuario user: b.getBancoDeUsuarios().values()){
            if(user.email.equals(email)){
                throw new ExceptionEmailJaCadastrado("Email já cadastrado");
            }
        }

    }

    public static void autenticarUsuario(String email,String senha) throws ExceptionSenhaInvalida, ExceptionEmailInvalido, ExceptionUsuarioNaoCadastrado {
        BancoDeUsuarios b = BancoDeUsuarios.getInstancia();
        if (b.getBancoDeUsuarios().isEmpty()){
            throw new ExceptionUsuarioNaoCadastrado("Email/Senha não cadastrados!");
        }
        for (Map.Entry<Integer, Usuario> user : b.getBancoDeUsuarios().entrySet()) {
            if (user.getValue().email.equals(email)) {
                if (user.getValue().senha.equals(senha)) {
                    Sessao.setIdDoUsuario(user.getKey());
                    // Autenticação bem-sucedida
                    return;
                } else {
                    throw new ExceptionSenhaInvalida("Senha incorreta!");
                }
            }
        }
        // Se sai do loop sem encontrar o email
        throw new ExceptionEmailInvalido("Email incorreto!");

    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nome = "+ nome + " email = " + email + " senha = " + senha + '}';
    }
}