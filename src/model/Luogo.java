package model;

public class Luogo {

    public String nome;
    public String indirizzo;
    public String stanza;
    public Integer piano;

    public Luogo(String name,String ind,String room,Integer p)
    {
        this.nome=name;
        this.indirizzo=ind;
        this.stanza=room;
        this.piano=p;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getStanza() {
        return stanza;
    }

    public void setStanza(String stanza) {
        this.stanza = stanza;
    }

    public Integer getPiano() {
        return piano;
    }

    public void setPiano(Integer piano) {
        this.piano = piano;
    }
}
