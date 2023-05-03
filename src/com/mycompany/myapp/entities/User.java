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
public class User {
    private Integer id;
    private String nom;
    private String prenom;
    private String photo_profile;
    private String email;
    private String password;
    private Date date_naissance;
    private Double point;
    private String phone;
    private String about;
    private String photo_couverture;
    private Date date_creation;

    public User(Integer id, String nom, String prenom, String photo_profile, String email, String password, Date date_naissance, Double point, String phone, String about, String photo_couverture, Date date_creation) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.photo_profile = photo_profile;
        this.email = email;
        this.password = password;
        this.date_naissance = date_naissance;
        this.point = point;
        this.phone = phone;
        this.about = about;
        this.photo_couverture = photo_couverture;
        this.date_creation = date_creation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Double getPoint() {
        return point;
    }

    public void setPoint(Double point) {
        this.point = point;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhoto_couverture() {
        return photo_couverture;
    }

    public void setPhoto_couverture(String photo_couverture) {
        this.photo_couverture = photo_couverture;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", photo_profile=" + photo_profile + ", email=" + email + ", password=" + password + ", date_naissance=" + date_naissance + ", point=" + point + ", phone=" + phone + ", about=" + about + ", photo_couverture=" + photo_couverture + ", date_creation=" + date_creation + '}';
    }
    
    
}
