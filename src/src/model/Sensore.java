package model;

import java.util.Date;

public class Sensore extends Dato {

    public String codices;
    public Boolean stato;
    public Integer massimale;

    public Sensore(String cod,Boolean state, Integer max,Integer v,String t,Date d)
    {
        super(v,t,d);
        this.codices=cod;
        this.stato=state;
        this.massimale=max;
    }

    public String getCodices() {
        return codices;
    }

    public void setCodices(String codices) {
        this.codices = codices;
    }

    public Boolean getStato() {
        return stato;
    }

    public void setStato(Boolean stato) {
        this.stato = stato;
    }

    public Integer getMassimale() {
        return massimale;
    }

    public void setMassimale(Integer massimale) {
        this.massimale = massimale;
    }
}
