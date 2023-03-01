
package com.mycompany.myapp.gui;
import com.mycompany.myapp.services.Groupeservice;
import com.codename1.components.SpanLabel;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.services.Groupeservice;

/**
 *
 * @author mehdi
 */
public class GroupeGUI extends Form {

    private final Groupeservice ts = Groupeservice.getInstance();

    public GroupeGUI() {
        super("groupe");

        this.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        SpanLabel sl = new SpanLabel();
        sl.setText(ts.fetchGroupe().toString());
        this.add(sl);

        this.getToolbar().addCommandToLeftBar("Back", null, e -> this.showBack());
    }
}