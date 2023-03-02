/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Form;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.NewsService;
import java.util.List;

/**
 *
 * @author ibert
 */
public class NewsGui extends Form {

    private final NewsService ts = NewsService.getInstance();

    public NewsGui() {
        super("News");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Get the list of News objects
        List<News> newsList = ts.fetchNews();

        // Loop through the list of News objects and add a button and label for each item
        for (News newsItem : newsList) {
            Button viewDetailsButton = new Button("View Details");
            Label titleLabel = new Label(newsItem.getTitre());
            titleLabel.setUIID("NewsTitleLabel");
            // Add a listener to the View Details button
            viewDetailsButton.addActionListener(evt -> {
                // Navigate to the news detail page
                // You can use the newsItem object to display the full details of the news
                new NewsDetailsGui(newsItem).show();
            });

            // Add the components to the form
            this.add(titleLabel);
            this.add(viewDetailsButton);
        }

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
        });
    }
}
