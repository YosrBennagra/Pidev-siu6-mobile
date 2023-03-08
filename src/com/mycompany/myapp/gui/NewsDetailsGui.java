/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.CommentaireNews;
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.services.CommentaireNewsService;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ibert
 */
public class NewsDetailsGui extends Form {

    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    public NewsDetailsGui(News newsItem) {
        super(newsItem.getTitre());
        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        try {
            enc = EncodedImage.create("/load.png");
        } catch (IOException ex) {

        }
        Label titleLabel = new Label(newsItem.getTitre());
        titleLabel.setUIID("NewsTitleLabel");
        SpanLabel descriptionLabel = new SpanLabel(newsItem.getDescription());
        descriptionLabel.setUIID("NewsDescriptionLabel");
        String url = Statics.URL_NEWS_PIC + newsItem.getImage();
        imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
        imgv = new ImageViewer(imgs);

        this.add(imgv);
        this.add(descriptionLabel);

        Label CommentSpaceLabel = new Label("Commentaires");
        
        CommentSpaceLabel.setAlignment(CENTER);
        this.add(CommentSpaceLabel);
        
        List<CommentaireNews> comments = CommentaireNewsService.getInstance().fetchCommentaireNews(newsItem.getId());
        for (CommentaireNews comment : comments) {
            Label usernameLabel = new Label(comment.getUsername()+" :");
            
            SpanLabel commentLabel = new SpanLabel("   "+comment.getDescription());
            
            
            this.add(usernameLabel);
            this.add(commentLabel);
            
            System.out.println(comment.getUsername());
        }

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new NewsGui().showBack();
        });
    }

}
