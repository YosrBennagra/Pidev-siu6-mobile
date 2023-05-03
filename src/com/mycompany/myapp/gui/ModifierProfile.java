/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;

/**
 *
 * @author nejdb
 */
public class ModifierProfile extends Form{
    
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
       public ModifierProfile() {
           super("Profile");
                    try {
                        enc = EncodedImage.create("/load.png");
                    } catch (IOException ex) {

                    }
                    String url = Statics.Photo_URL + SessionManager.getPhoto();
                    imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv = new ImageViewer(imgs);
           Container imageContainer = new Container(new BorderLayout());
                    imageContainer.add(BorderLayout.CENTER, imgv);
                   

                    this.add(imageContainer);
           
         this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        
        TextField nom = new TextField(SessionManager.getNom());
        Style style = nom.getStyle();
  
        TextField prenom = new TextField(SessionManager.getPrenom());
        this.addAll(nom,prenom);
        if("gamer".equals(SessionManager.getRole())){
          TextField tag = new TextField(SessionManager.getTag()); 
          this.add(tag);
        }
        TextField email = new TextField(SessionManager.getEmail());
        TextArea about  = new TextArea(SessionManager.getAbout());
        TextArea solde = new TextArea(SessionManager.getSolde()+"");
        this.addAll(solde,email,about);
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {new Acceuil().showBack();});
   
}
}
