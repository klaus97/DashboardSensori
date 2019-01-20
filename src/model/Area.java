package model;

import controller.JavaFXController;
import javafx.scene.control.Button;

public class Area {

    public Integer id;
    public String nome;
    public String regione;
    public Button link;

    public Area(Integer id,String name, String region)
    {
        this.id=id;
        this.nome=name;
        this.regione=region;
        this.link=new Button("view");

        //se viene cliccato apre la finestra per la gestione del massimale
        link.setOnAction(event -> new JavaFXController().setZona(event));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
