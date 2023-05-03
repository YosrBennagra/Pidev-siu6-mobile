/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.Userervice;

/**
 *
 * @author nejdb
 */
public class LoginGui extends Form {
    
  
    private final Userervice ts = Userervice.getInstance();

    public LoginGui() {
        super("Login");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel sl = new SpanLabel();
        TextField email = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField password=new TextField("", "Password", 20, TextArea.PASSWORD);
        Button submit = new Button("Login");
        SpanLabel signupgamer=new SpanLabel("sign in");
        submit.addActionListener((e) -> {
            if(!"".equals(email.getText()) &&  !"".equals(password.getText())){
              String result=ts.login(email.getText(), password.getText());
            if("succés".equals(result)){
                new Acceuil().show();
            }else{
                Dialog.show("Connexion echoué", result, "OK",null);
            }  
            }else{
               Dialog.show("Champ non remplit", "vous devez inserer les données", "OK",null); 
            }
            
           
            
           
        });
        Label forgot = new Label("forgot password");
        Label gameraccount = new Label("Crée Gamer compte?");
        Label coachaccount = new Label("Crée Coach compte?");
        gameraccount.addPointerPressedListener((e)->{
        new SignupGamer().show();
        });
         coachaccount.addPointerPressedListener((e)->{
        new SignupCoach().show();
        });
        forgot.addPointerPressedListener((e)->{
        String mail=email.getText();
        String result=ts.sendmail(mail);
        if("succés".equals(result)){
                Dialog.show("Email envoyée", "un email a été envoyer", "OK",null);
            }else{
                Dialog.show("Connexion echoué", "", "OK",null);
            }  
        });
        
        
        
        
        
        
        this.addAll(sl,signupgamer,email,password,submit,gameraccount,coachaccount,forgot);

        
    }  
}
