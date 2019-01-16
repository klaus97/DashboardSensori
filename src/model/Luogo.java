package model;

import controller.JavaFXController;
import javafx.scene.control.Button;

public class Luogo {

    public String nome;
    public String indirizzo;
    public String stanza;
    public Integer piano;
    private Button link;

    public Luogo(String name,String ind,String room,Integer p)
    {
        this.nome=name;
        this.indirizzo=ind;
        this.stanza=room;
        this.piano=p;
        this.link=new Button("view");

        //se viene cliccato apre la dashboard
        link.setOnAction(event -> new JavaFXController().setdashboard(event));
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

    public Button getLink() {
        return link;
    }

    public void setLink(Button link) {
        this.link = link;
    }
}
