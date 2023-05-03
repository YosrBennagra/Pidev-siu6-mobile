package com.mycompany.myapp.gui;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

public class Acceuil extends Form {

    public Acceuil() {
        super("Siuesport");

        Toolbar tb = this.getToolbar();
        this.add(new com.codename1.ui.Label("Siuesport"));
           
       
        tb.addMaterialCommandToSideMenu("Jeux", FontImage.MATERIAL_GAMES, (ActionListener) (ActionEvent evt) -> {
            JeuxGui jeuxGui = new JeuxGui();
            jeuxGui.show();
        });
        
         tb.addMaterialCommandToSideMenu(SessionManager.getNom(), FontImage.MATERIAL_ACCOUNT_CIRCLE, (ActionListener) (ActionEvent evt) -> {
            Profile profile = new Profile();
            profile.show();
        });

       
        
        
        
//        tb.addMaterialCommandToSideMenu("Actualite", FontImage.MATERIAL_PAGES, (ActionListener) (ActionEvent evt) -> {
//            ActualiteGui actualiteGui = new ActualiteGui();
//            actualiteGui.show();
//        });
//
//        tb.addMaterialCommandToSideMenu("Magasin", FontImage.MATERIAL_SHOP, (ActionListener) (ActionEvent evt) -> {
//            MagasinGui magasinGui = new MagasinGui();
//            magasinGui.show();
//        });
//
//        tb.addMaterialCommandToSideMenu("Tournoi", FontImage.MATERIAL_GAMEPAD, (ActionListener) (ActionEvent evt) -> {
//            TournoiGui tournoiGui = new TournoiGui();
//            tournoiGui.show();
//        });
//
//        tb.addMaterialCommandToSideMenu("Coaching", FontImage.MATERIAL_PEOPLE, (ActionListener) (ActionEvent evt) -> {
//            CoachingGui coachingGui = new CoachingGui();
//            coachingGui.show();
//        });
//
//        tb.addMaterialCommandToSideMenu("Groupe", FontImage.MATERIAL_GROUPS, (ActionListener) (ActionEvent evt) -> {
//            GroupeGui groupeGui = new GroupeGui();
//            groupeGui.show();
//        });

        this.show();
    }

}
