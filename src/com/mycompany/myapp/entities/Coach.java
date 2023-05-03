/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author nejdb
 */
public class Coach extends User {
   private Double review;
   private Double prix_heure;
   private String cv;

    public Coach(Double review, Double prix_heure, String cv, Integer id, String nom, String prenom, String photo_profile, String email, String password, Date date_naissance, Double point, String phone, String about, String photo_couverture, Date date_creation) {
        super(id, nom, prenom, photo_profile, email, password, date_naissance, point, phone, about, photo_couverture, date_creation);
        this.review = review;
        this.prix_heure = prix_heure;
        this.cv = cv;
    }

    public Double getReview() {
        return review;
    }

    public void setReview(Double review) {
        this.review = review;
    }

    public Double getPrix_heure() {
        return prix_heure;
    }

    public void setPrix_heure(Double prix_heure) {
        this.prix_heure = prix_heure;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "Coach{" + "review=" + review + ", prix_heure=" + prix_heure + ", cv=" + cv + '}';
    }
   
}
