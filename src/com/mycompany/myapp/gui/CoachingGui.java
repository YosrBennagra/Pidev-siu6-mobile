/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.MultiList;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

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

                String[] items = {"debutant", "intermediare", "avance"};
                ComboBox<String> comboBox = new ComboBox<>(items);

                Button submitBtn = new Button("Submit");

                //actions
                submitBtn.addActionListener((evt) -> {
                    if (ts.addCourse(new Cours(
                            titreTF.getText(),
                            descriptionTF.getText(),
                            videoTF.getText(),
                            imageTF.getText(),
                            Integer.parseInt(prixTF.getText()),
                            comboBox.getSelectedItem()
                    ))) {
                        Dialog.show("Success", "Task Inserted successfully", "Got it", null);
                    } else {
                        Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
                    }
                });

                addF.addAll(titreTF, descriptionTF, videoTF, imageTF, prixTF, comboBox, submitBtn);
                addF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new CoachingGui().showBack();
                });
                addF.show();
            }
        });

        showCoursesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                // create a new CoursesListView container
                CoursesListView listView = new CoursesListView();

                // fetch the courses
                List<Cours> courses = ts.fetchCourses();

                // add each course to the CoursesListView container
                for (Cours c : courses) {
                    listView.addItem(c.getTitre(), c.getDescription(), c.getPrix() + " DT", c.getNiveau(), new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            // handle click on the details button
                            // do something with the selected course
                        }
                    });
                }

                // create a new form and add the CoursesListView container to it
                Form showF = new Form("Show Courses", new BorderLayout());
                showF.addComponent(BorderLayout.CENTER, listView);

                showF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new CoachingGui().showBack();
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
