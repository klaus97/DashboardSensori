package model;

import controller.JavaFXController;
import javafx.scene.control.Button;

public class Zona {

    public String nome;
    public String provincia;
    private Button link;

    public Zona(String name,String prov)
    {
        this.nome=name;
        this.provincia=prov;
        this.link=new Button("view");

        //se viene cliccato apre la finestra per la gestione del massimale
        link.setOnAction(event -> new JavaFXController().setLuogo(event,this.getNome()));
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

    public Button getLink() {
        return link;
    }

    public void setLink(Button link) {
        this.link = link;
    }
}
