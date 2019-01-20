package model;

import controller.JavaFXController;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import java.util.Date;

public class Sensore extends Dato {

    public String codices;
    public Boolean stato;
    public Integer massimale;
    public Hyperlink linkmax;
    public Integer frequenza;

    public Sensore(String cod,Boolean state, Integer max,Integer freq,Integer v,String t,Date d)
    {
        super(v,t,d);
        this.codices=cod;
        this.stato=state;
        this.massimale=max;
        this.frequenza=freq;
        this.linkmax=new Hyperlink(this.getMassimale().toString());

        //se viene cliccato apre la finestra per la gestione del massimale
        linkmax.setOnMouseClicked((MouseEvent mouseEvent) ->
        {
            JavaFXController.setGestionemax(new Sensore(this.getCodices(),null,this.getMassimale(),0,null,null,null));
        });
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

    public Integer getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(Integer frequenza) {
        this.frequenza = frequenza;
    }

    public Hyperlink getLinkmax() {
        return linkmax;
    }

    public void setLinkmax(Hyperlink linkmax) {
        this.linkmax = linkmax;
    }
}
