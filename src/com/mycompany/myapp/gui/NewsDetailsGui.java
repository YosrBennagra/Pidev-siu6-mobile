/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;

/**
 *
 * @author ibert
 */
public class NewsDetailsGui extends Form {

    public NewsDetailsGui(News newsItem) {
        super(newsItem.getTitre());
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        // Create a label for the title and description
        Label titleLabel = new Label(newsItem.getTitre());
        titleLabel.setUIID("NewsTitleLabel");
        SpanLabel descriptionLabel = new SpanLabel(newsItem.getDescription());
        descriptionLabel.setUIID("NewsDescriptionLabel");
        
        // Add the components to the form
        this.add(titleLabel);
        this.add(descriptionLabel);
        
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new NewsGui().showBack();
        });
    }
}

