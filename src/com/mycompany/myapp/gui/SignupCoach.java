/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Calendar;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.Userervice;

/**
 *
 * @author nejdb
 */
public class SignupCoach extends Form {
    
  
    private final Userervice ts = Userervice.getInstance();

    public SignupCoach() {
        super("SignUp");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        SpanLabel sl = new SpanLabel();
        TextField nom = new TextField("", "Nom", 20, TextArea.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextArea.ANY);
        TextField phone = new TextField("", "Numéro Téléphone", 20, TextArea.PHONENUMBER);
        Calendar naissance = new Calendar();
        TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
         TextField about = new TextField("", "About", 20, TextArea.ANY);
        TextField password=new TextField("", "Password", 20, TextArea.PASSWORD);
        Button submit = new Button("crée compte Coach");
        submit.addActionListener((e) -> {
            
            
           
        });
        this.addAll(sl,nom,prenom,phone,naissance,email,about,password,submit);
        this.getToolbar().addCommandToLeftBar("Back", null, e -> new LoginGui().showBack());
        
    }  
}

