package com.project.project_oop_java.model;

public class Questao {
    private String idDoCriador;
    private String fonte;
    private String enunciado;
    private String totalDePontos;
    private String alternativaA;
    private String alternativaB;
    private String alternativaC;
    private String alternativaD;
    private String alternativaE;
    private String respostaCorreta;

    public Questao(String idDoCriador ,String fonte, String enunciado, String totalDePontos, String alternativaA, String alternativaB, String alternativaC, String alternativaD, String alternativaE, String respostaCorreta) {
        this.idDoCriador = idDoCriador;
        this.fonte = fonte;
        this.enunciado = enunciado;
        this.totalDePontos = totalDePontos;
        this.alternativaA = alternativaA;
        this.alternativaB = alternativaB;
        this.alternativaC = alternativaC;
        this.alternativaD = alternativaD;
        this.alternativaE = alternativaE;
        this.respostaCorreta = respostaCorreta;
    }

    public String getIdDoCriador() {
        return idDoCriador;
    }

    public void setIdDoCriador(String idDoCriador) {
        this.idDoCriador = idDoCriador;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getTotalDePontos() {
        return totalDePontos;
    }

    public void setTotalDePontos(String totalDePontos) {
        this.totalDePontos = totalDePontos;
    }

    public String getAlternativaA() {
        return alternativaA;
    }

    public void setAlternativaA(String alternativaA) {
        this.alternativaA = alternativaA;
    }

    public String getAlternativaB() {
        return alternativaB;
    }

    public void setAlternativaB(String alternativaB) {
        this.alternativaB = alternativaB;
    }

    public String getAlternativaC() {
        return alternativaC;
    }

    public void setAlternativaC(String alternativaC) {
        this.alternativaC = alternativaC;
    }

    public String getAlternativaD() {
        return alternativaD;
    }

    public void setAlternativaD(String alternativaD) {
        this.alternativaD = alternativaD;
    }

    public String getAlternativaE() {
        return alternativaE;
    }

    public void setAlternativaE(String alternativaE) {
        this.alternativaE = alternativaE;
    }

    @Override
    public String toString() {
        return "Questao{" +
                "fonte='" + fonte + '\'' +
                ", enunciado='" + enunciado + '\'' +
                ", totalDePontos='" + totalDePontos + '\'' +
                ", alternativaA='" + alternativaA + '\'' +
                ", alternativaB='" + alternativaB + '\'' +
                ", alternativaC='" + alternativaC + '\'' +
                ", alternativaD='" + alternativaD + '\'' +
                ", alternativaE='" + alternativaE + '\'' +
                '}';
    }
}
