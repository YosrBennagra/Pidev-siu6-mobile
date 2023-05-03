/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.services.Userervice;
import java.util.List;

/**
 *
 * @author nejdb
 */
public class SignupGamer extends Form {
    
  
    private final Userervice ts = Userervice.getInstance();

    public SignupGamer() {
        super("SignUp");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        SpanLabel sl = new SpanLabel();
        TextField tag = new TextField("", "Pseudo", 20, TextArea.ANY);
        TextField nom = new TextField("", "Nom", 20, TextArea.ANY);
        TextField prenom = new TextField("", "Prenom", 20, TextArea.ANY);
        TextField phone = new TextField("", "Numéro Téléphone", 20, TextArea.PHONENUMBER);
        TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        TextField about = new TextField("", "About", 20, TextArea.ANY);
        TextField password=new TextField("", "Password", 20, TextArea.PASSWORD);
        Button submit = new Button("crée compte Gamer");
        submit.addActionListener((e) -> {
            if(!"".equals(tag.getText())){
                if(!"".equals(nom.getText())){
                    if(!"".equals(prenom.getText())){
                        if(!"".equals(email.getText())){
                            if(!"".equals(password.getText())){  
           List<String> result=ts.signupgamer(tag.getText(),nom.getText(),prenom.getText(),phone.getText(),datePicker.getDate(),about.getText(),email.getText(), password.getText());
            if("200".equals(result.get(0))){
                Dialog.show("Succés", "votre compte été ajouter", "OK",null);
                new LoginGui().showBack();
            }else if("403".equals(result.get(0))){
                Dialog.show("Mail utilisé", result.get(1), "OK",null);
            }else{
                Dialog.show("Echec", "compte n'est pas ajouter", "OK",null);
            }}
            else{
                 Dialog.show("Echec", "Password ne doit pas etre vide", "OK",null);
                } 
             }else{
                 Dialog.show("Echec", "Email ne doit pas etre vide", "OK",null);
                } 
            
            
            }else{
                 Dialog.show("Echec", "Prenom ne doit pas etre vide", "OK",null);
                } 
                }else{
                 Dialog.show("Echec", "Nom ne doit pas etre vide", "OK",null);
                }           
            }else{
                 Dialog.show("Echec", "Pseudo ne doit pas etre vide", "OK",null);
            }
        });
        
        this.addAll(sl,tag,nom,prenom,phone,datePicker,email,about,password,submit);
        this.getToolbar().addCommandToLeftBar("Back", null, e -> new LoginGui().showBack());

        
    }  
}
