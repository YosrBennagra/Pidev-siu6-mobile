/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author ibert
 */
public class News {
    private Integer id;
    private String titre;
    private String dateN;
    private String description;
    private String image;

    public News(Integer id, String titre, String dateN, String description, String image) {
        this.id = id;
        this.titre = titre;
        this.dateN = dateN;
        this.description = description;
        this.image = image;
    }

    public News(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }
    

    public News() {
       
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDateN() {
        return dateN;
    }

    public void setDateN(String dateN) {
        this.dateN = dateN;
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
    return "\ntitre: " + titre + "\n" +
            "description: " + description + "\n";
}
}
