/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.CourseService;

/**
 *
 * @author jallouli
 */
public class CoachingGui extends Form {

    private final CourseService ts = CourseService.getInstance();

    public CoachingGui() {
        super("course");
        //custom
        this.setLayout(BoxLayout.yCenter());
        this.setTitle("Home");

        //widgets
        Button addCourseBtn = new Button("Add Cours");
        Button showCoursesBtn = new Button("Show Courses");

        addCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Form addF = new Form("add Courses", BoxLayout.y());
                //Widgets
                TextField titreTF = new TextField("", "Cour's name");
                TextField descriptionTF = new TextField("", "Cour's description");
                TextField videoTF = new TextField("", "Cour's video");
                TextField imageTF = new TextField("", "Cour's image");
                TextField prixTF = new TextField("", "Cour's prix");
                TextField niveauTF = new TextField("", "Cour's niveau");
                Button submitBtn = new Button("Submit");

                //actions
                submitBtn.addActionListener((evt) -> {
                    if (ts.addCourse(new Cours(
                            titreTF.getText(),
                            descriptionTF.getText(),
                            videoTF.getText(),
                            imageTF.getText(),
                            Integer.parseInt(prixTF.getText()),
                            niveauTF.getText()
                    ))) {
                        Dialog.show("Success", "Task Inserted successfully", "Got it", null);
                    } else {
                        Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
                    }
                });

                addF.addAll(titreTF, descriptionTF, videoTF, imageTF, prixTF, niveauTF, submitBtn);
                addF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new Acceuil().showBack();
                });
                addF.show();
            }
        });

        showCoursesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Form showF = new Form("show Courses", BoxLayout.y());
                SpanLabel sl = new SpanLabel();
                sl.setText(ts.fetchCourses().toString());
                showF.add(sl);

                showF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new Acceuil().showBack();
                });
                showF.show();
            }
        });

        this.addAll(addCourseBtn, showCoursesBtn);
        this.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
            new Acceuil().showBack();
        });
        this.show();
    }

}
