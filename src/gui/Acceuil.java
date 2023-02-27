/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import static com.codename1.ui.CN.CENTER;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author ibert
 */
public class Acceuil {
    Form Home;
    Form f1;
    Form f2;
    

    public Acceuil(Form f1, Form f2) {
        f1 = new Form(new FlowLayout(CENTER, CENTER));
        f1.add(new Label("Web Site"));
        f2 = new Form(new FlowLayout(CENTER, CENTER));
        f2.add(new Label("Settings"));
        Home = new Form("sidebar Menu");
    }

}
