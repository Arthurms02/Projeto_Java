package com.project.project_oop_java.model;


import com.project.project_oop_java.exceptions.ExceptionCampoVazio;
import com.project.project_oop_java.exceptions.ExceptionLinkInvalido;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sala {

    private String nomeDaSala;
    private String donoDaSala;
    private int codigoDaSala;
    private ArrayList<String> materialApoio;
    private ArrayList<Questao> questoes;
    private ArrayList<Usuario> participantes;

    public Sala(String nomeDaSala, String donoDaSala, int codigoDaSala) {
        this.nomeDaSala = nomeDaSala;
        this.donoDaSala = donoDaSala;
        this.codigoDaSala = codigoDaSala;
        this.materialApoio = new ArrayList<>();
        this.questoes = new ArrayList<>();
        this.participantes = new ArrayList<>();
    }

    public void validarMaterial(String url)throws ExceptionLinkInvalido {
        final String YOUTUBE_URL_REGEX =
                "^(?:https?://)?(?:www\\.)?(?:youtube\\.com/watch\\?v=|youtu\\.be/)([a-zA-Z0-9_-]{11})(?:[?&]\\S*)?$";

        final Pattern YOUTUBE_URL_PATTERN = Pattern.compile(YOUTUBE_URL_REGEX);
        Matcher matcher = YOUTUBE_URL_PATTERN.matcher(url);
        if (!matcher.matches()) {
            throw new ExceptionLinkInvalido("Só são permitido links da plataforma do Youtube. Apague os campos e tente novamente!");
        }
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

    public int getCodigoDaSala() {
        return codigoDaSala;
    }

    public void setCodigoDaSala(int codigoDaSala) {
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

    public void setQuestoes(Questao questao) {
        this.questoes.add(questao);
    }

    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Usuario participante) {
        this.participantes.add(participante);
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nomeDaSala='" + nomeDaSala + '\'' +
                ", donoDaSala='" + donoDaSala + '\'' +
                ", codigoDaSala=" + codigoDaSala +
                ", materialApoio=" + materialApoio +
                ", questoes=" + questoes +
                ", participantes=" + participantes +
                '}';
    }
}
