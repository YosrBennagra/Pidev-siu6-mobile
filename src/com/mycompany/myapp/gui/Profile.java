/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;

/**
 *
 * @author nejdb
 */
public class Profile extends Form  {
    
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;
       public Profile() {
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
        
        SpanLabel nom = new SpanLabel("Nom : "+SessionManager.getNom());
        SpanLabel prenom = new SpanLabel("Prenom : "+SessionManager.getPrenom());
        this.addAll(nom,prenom);
        if("gamer".equals(SessionManager.getRole())){
          SpanLabel tag = new SpanLabel("Tag : "+SessionManager.getTag()); 
          this.add(tag);
        }
        SpanLabel email = new SpanLabel("Email : "+SessionManager.getEmail());
        SpanLabel about  = new SpanLabel("About : "+SessionManager.getAbout());
        SpanLabel solde = new SpanLabel("Solde : "+SessionManager.getSolde()+"");
        this.addAll(solde,email,about);
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {new Acceuil().showBack();});
   this.getToolbar().addMaterialCommandToRightBar("", FontImage.MATERIAL_EDIT, (evt) -> {new ModifierProfile().show();});
   
        
    }
}
