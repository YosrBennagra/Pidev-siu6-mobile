/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Form;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.NewsService;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ibert
 */
public class NewsGui extends Form {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
    private final NewsService ts = NewsService.getInstance();

    public NewsGui() {
        super("News");
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        List<News> newsList = ts.fetchNews();
        for (News newsItem : newsList) {
            try {
                enc = EncodedImage.create("/load.png");
            } catch (IOException ex) {

            }
            String url = Statics.URL_NEWS_PIC + newsItem.getImage();
            imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
            imgv = new ImageViewer(imgs);
            Button viewDetailsButton = new Button("View Details");
            Label titleLabel = new Label(newsItem.getTitre());
            titleLabel.setUIID("CenterTitle");
            titleLabel.setAlignment(CENTER);
            viewDetailsButton.addActionListener(evt -> {
                new NewsDetailsGui(newsItem).show();
            });
            this.add(imgv);
            this.add(titleLabel);
            this.add(viewDetailsButton);
        }

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
        });
    }
}
