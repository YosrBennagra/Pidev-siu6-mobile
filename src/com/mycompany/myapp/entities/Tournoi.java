/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author bayou
 */
public class Tournoi {
     //var
    private int id;
    private int nb_team;
    private int nb_joueur_team;
    private String nomtournoi;
    private String device;
    private String image;
     
    //constructor

    public Tournoi() {
    }

    public Tournoi(int nb_team, int nb_joueur_team, String nomtournoi, String device, String image) {
        this.nb_team = nb_team;
        this.nb_joueur_team = nb_joueur_team;
        this.nomtournoi = nomtournoi;
        this.device = device;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNb_team() {
        return nb_team;
    }

    public void setNb_team(int nb_team) {
        this.nb_team = nb_team;
    }

    public int getNb_joueur_team() {
        return nb_joueur_team;
    }

    public void setNb_joueur_team(int nb_joueur_team) {
        this.nb_joueur_team = nb_joueur_team;
    }

    public String getNomtournoi() {
        return nomtournoi;
    }

    public void setNomtournoi(String nomtournoi) {
        this.nomtournoi = nomtournoi;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Tournoi{" + "id=" + id + ", nb_team=" + nb_team + ", nb_joueur_team=" + nb_joueur_team + ", nomtournoi=" + nomtournoi + ", device=" + device + ", image=" + image + '}';
    }
  
}
