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
public class Gamer extends User {
    private String tag;

    public Gamer(String tag, Integer id, String nom, String prenom, String photo_profile, String email, String password, Date date_naissance, Double point, String phone, String about, String photo_couverture, Date date_creation) {
        super(id, nom, prenom, photo_profile, email, password, date_naissance, point, phone, about, photo_couverture, date_creation);
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Gamer{" + "tag=" + tag + '}';
    }
    
}
