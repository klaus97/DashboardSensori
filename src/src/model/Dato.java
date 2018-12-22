package model;

import java.util.Date;

public class Dato {

    public Integer valore;
    public String tipo;
    public Date datainvio;

    public Dato(Integer val,String type,Date data)
    {
        this.valore=val;
        this.tipo=type;
        this.datainvio=data;
    }

    public Integer getValore() {
        return valore;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDatainvio() {
        return datainvio;
    }

    public void setDatainvio(Date datainvio) {
        this.datainvio = datainvio;
    }
}
