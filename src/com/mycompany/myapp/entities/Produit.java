package com.mycompany.myapp.entities;

public class Produit {

    //var
    private int id;
    private String nom;
    private float prix;
    private String description;
    private String image;

    public Produit() {
    }

    public Produit(String nom, float prix, String description, String image) {
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Produit{" + "id=" + id + ", nom=" + nom + ", prix=" + prix + ", description=" + description + ", image=" + image + '}';
    }


 


}