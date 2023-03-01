package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.JeuxService;

public class JeuxGui extends Form {

    private final JeuxService ts = JeuxService.getInstance();

    public JeuxGui() {
        super("Jeux");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchJeux().toString());
        this.add(sl);

        this.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            new Acceuil().showBack();
       });
    }
}