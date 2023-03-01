/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.NewsService;

/**
 *
 * @author ibert
 */
public class NewsGui extends Form {

    private final NewsService ts = NewsService.getInstance();

    public NewsGui() {
        super("News");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchNews().toString());
        this.add(sl);

       this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
       });
    }
}
