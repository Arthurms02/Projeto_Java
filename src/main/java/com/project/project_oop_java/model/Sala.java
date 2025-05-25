package com.project.project_oop_java.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Sala {

    private String nomeDaSala;
    private String donoDaSala;
    private String codigoDaSala;
    private ArrayList<String> materialApoio;
    private ArrayList<Questao> questoes;
    private ArrayList<Usuario> participantes;

    public Sala(String nomeDaSala, String donoDaSala, String codigoDaSala) {
        this.nomeDaSala = nomeDaSala;
        this.donoDaSala = donoDaSala;
        this.codigoDaSala = codigoDaSala;
        this.materialApoio = new ArrayList<>();
        this.questoes = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }

    public String getNomeDaSala() {
        return nomeDaSala;
    }

    public void setNomeDaSala(String nomeDaSala) {
        this.nomeDaSala = nomeDaSala;
    }

    public String getDonoDaSala() {
        return donoDaSala;
    }

    public void setDonoDaSala(String donoDaSala) {
        this.donoDaSala = donoDaSala;
    }

    public String getCodigoDaSala() {
        return codigoDaSala;
    }

    public void setCodigoDaSala(String codigoDaSala) {
        this.codigoDaSala = codigoDaSala;
    }

    public ArrayList<String> getMaterialApoio() {
        return materialApoio;
    }

    public void setMaterialApoio(String materialApoio) {
        this.materialApoio.add(materialApoio);
    }

    public ArrayList<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(ArrayList<Questao> questoes) {
        this.questoes = questoes;
    }

    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(ArrayList<Usuario> participantes) {
        this.participantes = participantes;
    }
}
