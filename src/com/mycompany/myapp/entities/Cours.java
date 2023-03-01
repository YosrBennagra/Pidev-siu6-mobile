/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author jallouli
 */
public class Cours {
    
    private int id;
    private String titre;
    private String description;
    private String video;
    private String image;
    private int prix;
    private String niveau;

    public Cours() {
    }

    public Cours(String titre, String description, String video, String image,int prix, String niveau) {
        this.titre = titre;
        this.description = description;
        this.video = video;
        this.image = image;
        this.prix = prix;
        this.niveau = niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return "Cours{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", video=" + video + ", image=" + image + ", prix=" + prix + ", niveau=" + niveau + '}';
    }
}
