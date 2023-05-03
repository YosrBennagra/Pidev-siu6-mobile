/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author nejdb
 */
public class SessionManager {
    
    public static Preferences pref ; 
    private static Double id ; 
    private static String nom ; 
    private static String prenom ;
    private static String email;
    private static String about;
    private static String tag;
    private static String photo;
    private static String role;
    private static Double solde;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static Double getId() {
        return id;
    }

    public static void setId(Double id) {
        SessionManager.id = id;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getAbout() {
        return about;
    }

    public static void setAbout(String about) {
        SessionManager.about = about;
    }

    public static String getTag() {
        return tag;
    }

    public static void setTag(String tag) {
        SessionManager.tag = tag;
    }

    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        SessionManager.photo = photo;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

    public static Double getSolde() {
        return solde;
    }

    public static void setSolde(Double solde) {
        SessionManager.solde = solde;
    }
    
    
}
