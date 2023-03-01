/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.Tournoiservice;
/**
 *
 * @author bayou
 */
public class TournoiGui extends Form {

    //var
    Tournoiservice ts = Tournoiservice.getInstance();

    public TournoiGui() {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Tournois");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
        });

        //widgets
        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchTournois().toString());

        //end
        this.add(sl);

    }

}
