package model;

public class Zona {

    public String nome;
    public String provincia;

    public Zona(String name,String prov)
    {
        this.nome=name;
        this.provincia=prov;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
