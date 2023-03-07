package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;

public class CoursesListView extends Container {

    private Container listItems;

    public CoursesListView() {
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        getStyle().setBgColor(0xFFFFFF);

        listItems = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        listItems.getStyle().setPadding(Component.LEFT, 20);
        listItems.getStyle().setPadding(Component.RIGHT, 20);
        add(listItems);
    }

    public void addItem(String title, String description, String price, String level, ActionListener listener, ActionListener deleteListener, ActionListener updateListener) {
        Container item = new Container(new BorderLayout());
        item.getStyle().setPadding(10, 10, 10, 10);
        item.getStyle().setBgColor(0xEEEEEE);
        item.getStyle().setMargin(Component.BOTTOM, 20);

        Label titleLabel = new Label(title);
        titleLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_MEDIUM));
        titleLabel.getUnselectedStyle().setFgColor(0x000000);
        item.add(BorderLayout.NORTH, titleLabel);

        Label descriptionLabel = new Label(description);
        descriptionLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        descriptionLabel.getUnselectedStyle().setFgColor(0x808080);
        item.add(BorderLayout.NORTH, descriptionLabel);

        Container footer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        footer.getStyle().setMargin(Component.TOP, 10);

        Label priceLabel = new Label(price);
        priceLabel.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_SMALL));
        priceLabel.getUnselectedStyle().setFgColor(0x000000);
        item.add(BorderLayout.CENTER, priceLabel);

        Button detailsBtn = new Button("Details");
        detailsBtn.addActionListener(listener);
        footer.add(detailsBtn);

        Button deleteBTN = new Button("delete");
        deleteBTN.addActionListener(deleteListener);
        deleteBTN.setUIID("CustomDeleteButton"); // set custom UIID
        deleteBTN.getAllStyles().setBgColor(0xFFFFF);
        footer.add(deleteBTN);

        Button updateBTN = new Button("update");
        updateBTN.addActionListener(updateListener);
        updateBTN.setUIID("CustomUpdateButton"); // set custom UIID
        updateBTN.getAllStyles().setBgColor(0xFF0000);
        footer.add(updateBTN);

        item.add(BorderLayout.SOUTH, footer);

        listItems.add(item);
    }

}
