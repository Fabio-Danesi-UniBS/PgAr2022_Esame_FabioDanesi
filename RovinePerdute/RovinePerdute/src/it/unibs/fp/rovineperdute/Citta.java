package it.unibs.fp.rovineperdute;

import java.util.*;

public class Citta implements Comparable<Citta>{

    private int x;
    private int y;
    private int altitudine;
    private String nome;
    private int id;
    private List<Archi> cittaCollegate;
    private Citta cittaAttuale;
    private int distanza;
    private boolean visitato;

    public Citta(int x, int y, int altitudine, String nome, int id, List<Archi> cittaCollegate) {
        this.x = x;
        this.y = y;
        this.altitudine = altitudine;
        this.nome = nome;
        this.id = id;
        this.cittaCollegate = cittaCollegate;
    }

    public Citta() {
        this.distanza =  Integer.MAX_VALUE;
        this.cittaCollegate =  new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAltitudine() {
        return altitudine;
    }

    public void setAltitudine(int altitudine) {
        this.altitudine = altitudine;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Archi> getCittaCollegate() {
        return cittaCollegate;
    }

    public void setCittaCollegate(ArrayList<Archi> cittaCollegate) {
        this.cittaCollegate = cittaCollegate;
    }

    public Citta getCittaAttuale() {
        return cittaAttuale;
    }

    public void setCittaAttuale(Citta cittaAttuale) {
        this.cittaAttuale = cittaAttuale;
    }

    public int getDistanza() {
        return distanza;
    }

    public void setDistanza(int distanza) {
        this.distanza = distanza;
    }

    public boolean isVisitato() {
        return visitato;
    }

    public void setVisitato(boolean visitato) {
        this.visitato = visitato;
    }

    public int compareTo(Citta cit){
        return Integer.compare(this.distanza, cit.getDistanza());
    }

}
