package model;

public class Area {

    public String nome;
    public String regione;

    public Area(String name,String region)
    {
        this.nome=name;
        this.regione=region;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }
}
