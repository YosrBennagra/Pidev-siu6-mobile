package com.mycompany.myapp.entities;

public class Groupe {

    //var
    private int id;
    private String Nom_groupe;
    private String image;
    private String description;
    private int idOwner;
    private int etat;

    public Groupe( String Nom_groupe, String image, String description) {

        this.Nom_groupe = Nom_groupe;
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Groupe() {
    }

    public String getNom_groupe() {
        return Nom_groupe;
    }

    public void setNom_groupe(String Nom_groupe) {
        this.Nom_groupe = Nom_groupe;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(int idOwner) {
        this.idOwner = idOwner;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Groupe{" + "id=" + id + ", Nom_groupe=" + Nom_groupe + ", image=" + image + ", description=" + description + ", idOwner=" + idOwner + ", etat=" + etat + '}';
    }











}