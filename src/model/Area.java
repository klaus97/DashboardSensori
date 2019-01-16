package model;

import controller.JavaFXController;
import javafx.scene.control.Button;

public class Area {

    public String nome;
    public String regione;
    public Button link;

    public Area(String name,String region)
    {
        this.nome=name;
        this.regione=region;
        this.link=new Button("view");

        //se viene cliccato apre la finestra per la gestione del massimale
        link.setOnAction(event -> new JavaFXController().setZona(event));
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

    public Button getLink() {
        return link;
    }

    public void setLink(Button link) {
        this.link = link;
    }
}
