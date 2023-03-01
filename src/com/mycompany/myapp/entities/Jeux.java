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
public class Jeux {

    private Integer id;
    private String nomGame;
    private String dateAddGame;
    private Integer maxPlayers;
    private String image;
    private String description;

    public Jeux() {
    }

    public Jeux(Integer id, String nomGame, String dateAddGame, Integer maxPlayers, String image, String description) {
        this.id = id;
        this.nomGame = nomGame;
        this.dateAddGame = dateAddGame;
        this.maxPlayers = maxPlayers;
        this.image = image;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomGame() {
        return nomGame;
    }

    public void setNomGame(String nomGame) {
        this.nomGame = nomGame;
    }

    public String getDateAddGame() {
        return dateAddGame;
    }

    public void setDateAddGame(String dateAddGame) {
        this.dateAddGame = dateAddGame;
    }

    public Integer getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
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

    @Override
public String toString() {
    return "\nGame Name: " + nomGame + "\n" +
            "Image URL: " + image + "\n" +
            "Description: " + description+ "\n";
}
    

}
