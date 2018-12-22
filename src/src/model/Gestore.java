package model;

public class Gestore {

    public String codice;
    public String nome;
    public String cognome;
    public String ruolo;

    public Gestore(String cod,String name,String cogn,String tipo)
    {
        this.codice=cod;
        this.nome=name;
        this.cognome=cogn;
        this.codice=tipo;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
}
