package com.project.project_oop_java.model;

import com.project.project_oop_java.exceptions.ExceptionEmailInvalido;
import com.project.project_oop_java.exceptions.ExceptionEmailJaCadastrado;
import com.project.project_oop_java.exceptions.ExceptionSenhaInvalida;
import com.project.project_oop_java.exceptions.ExceptionUsuarioNaoCadastrado;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void testeCadastraAluno() {
        Usuario u = Usuario.cadastraUsuario("Arthur", "Arthur@test.com", "1234", "Aluno");

        assertTrue(u instanceof Aluno);
        assertEquals("Arthur", u.getNome());
    }

    @Test
    void testeCadastraProfessor() {
        Usuario u = Usuario.cadastraUsuario("Pablo", "Pablo@test.com", "1234", "Professor");

        assertTrue(u instanceof Professor);
        assertEquals("Pablo", u.getNome());
    }

    @Test
    void testeCadastraOutros() {
        Usuario u = Usuario.cadastraUsuario("Arthur", "Arthur@test.com", "1234", "Outros");

        assertTrue(u instanceof Outros);
        assertEquals("Arthur", u.getNome());
    }

    @Test
    void testeConfirmaCamposEmailRegistro() {
        boolean usuario1 = Usuario.confirmaEmailRegistro("test@test.com","test@test.com");

        assertEquals(true, usuario1);

        boolean usuario2 = Usuario.confirmaEmailRegistro("test@test.com" , "testee@teste.com");

        assertEquals(false , usuario2);
    }

    @Test
    void testeValidarEmail() throws ExceptionEmailInvalido {

        assertThrows(ExceptionEmailInvalido.class, ()->{
            Usuario.validarEmail("test@.com");
        });

    }

    @Test
    void testeValidarEmailJaCadastrado() throws ExceptionEmailJaCadastrado {
        Aluno usuario1 = new Aluno("Arthur", "Arthur@test.com", "1234", TipoDeUsuario.ALUNO);

        BancoDeUsuarios banco = BancoDeUsuarios.getInstancia();
        banco.cadastrarNoBanco(usuario1);

        assertThrows(ExceptionEmailJaCadastrado.class,()->{
           Usuario.validarEmailJaCadastrado("Arthur@test.com");
        });

    }

    @Test
    void testeAutenticarUsuarioNaoCadastrado() throws ExceptionUsuarioNaoCadastrado {
        Aluno usuario = new Aluno("Arthur", "Arthur@test.com", "1234", TipoDeUsuario.ALUNO);

        BancoDeUsuarios banco = BancoDeUsuarios.getInstancia();

        assertThrows(ExceptionUsuarioNaoCadastrado.class,()->{
            Usuario.autenticarUsuario("Arthur@test.com", "1234");
        });

    }

    @Test
    void testeAutenticarEmail() throws ExceptionEmailInvalido {
        Aluno usuario = new Aluno("Arthur", "Arthur@test.com", "1234", TipoDeUsuario.ALUNO);

        BancoDeUsuarios banco = BancoDeUsuarios.getInstancia();
        banco.cadastrarNoBanco(usuario);

        assertThrows(ExceptionEmailInvalido.class,()->{
            Usuario.autenticarUsuario("Arthur@gmail.com", "1234");
        });

    }

    @Test
    void testeAutenticarSenha() throws ExceptionSenhaInvalida {
        Aluno usuario = new Aluno("Arthur", "Arthur@test.com", "1234", TipoDeUsuario.ALUNO);

        BancoDeUsuarios banco = BancoDeUsuarios.getInstancia();
        banco.cadastrarNoBanco(usuario);

        assertThrows(ExceptionSenhaInvalida.class,()->{
            Usuario.autenticarUsuario("Arthur@test.com", "4321");
        });

    }

}