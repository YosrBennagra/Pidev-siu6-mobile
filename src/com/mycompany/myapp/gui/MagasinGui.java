package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.ProduitService;

/**
 *
 * @author kamel
 */
public class MagasinGui extends Form {

    //var
    ProduitService ts = ProduitService.getInstance();

    public MagasinGui() {

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("All Produits");
        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
        });

        //widgets

        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchProduits().toString());

        //end
        this.add(sl);

    }

}