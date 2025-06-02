package com.project.project_oop_java.model;


import com.project.project_oop_java.exceptions.ExceptionLinkInvalido;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sala {

    private String idDoCriador;
    private String nomeDaSala;
    private String donoDaSala;
    private int codigoDaSala;
    private ArrayList<String> materialApoio;
    private ArrayList<Usuario> participantes;

    public Sala(String idDoCriador,String nomeDaSala, String donoDaSala, int codigoDaSala) {
        this.idDoCriador = idDoCriador;
        this.nomeDaSala = nomeDaSala;
        this.donoDaSala = donoDaSala;
        this.codigoDaSala = codigoDaSala;
        this.materialApoio = new ArrayList<>();
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



    public ArrayList<Usuario> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(Usuario participante) {
        this.participantes.add(participante);
    }

    public String getIdDoCriador() {
        return idDoCriador;
    }

    public void setIdDoCriador(String idDoCriador) {
        this.idDoCriador = idDoCriador;
    }

    @Override
    public String toString() {
        return "Sala{" +
                "idDoCriador='" + idDoCriador + '\'' +
                ", nomeDaSala='" + nomeDaSala + '\'' +
                ", donoDaSala='" + donoDaSala + '\'' +
                ", codigoDaSala=" + codigoDaSala +
                ", materialApoio=" + materialApoio +
                ", participantes=" + participantes +
                '}';
    }

    public String formatarString(){
        Professor p = new Professor("Arthut","test@tes.com","123",TipoDeUsuario.PROFESSOR, "TESTE");
        setParticipantes(p);

        String arrayMateriais = String.join(";",materialApoio);
        ArrayList<String> formatUser = new ArrayList<>();
        for(Usuario u: participantes){
            formatUser.add(u.formatarString()+":");
        }
        formatUser.trimToSize();
        String arrayParticipantes = String.join(";",formatUser);

        return idDoCriador +";"+ nomeDaSala + ";" + donoDaSala + ";" + arrayMateriais + "/" + arrayParticipantes;
    }
}
