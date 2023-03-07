/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.services.CourseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.mycompany.myapp.utilities.Statics;
import com.codename1.ui.Font;
import com.codename1.ui.Component;
import com.codename1.io.JSONParser;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComponentSelector;
import com.codename1.components.ImageViewer;
import com.mycompany.myapp.entities.League;

/**
 *
 * @author jallouli
 */
public class CoachingGui extends Form {

    int coachId = 2;
    EncodedImage enc;
    Image imgs;
    ImageViewer imgv;

    private final CourseService ts = CourseService.getInstance();

    public CoachingGui() {
        super("course");

        //custom
        this.setLayout(BoxLayout.y());
        this.setTitle("Home");

        //widgets
        Button lolDetails = new Button("League data");
        Button addCourseBtn = new Button("Add Cours");
        lolDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Form leagueF = new Form("league data", BoxLayout.y());
                TextField leagueName = new TextField("", "ur league name");
                Button submitBtn = new Button("Submit");
                submitBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent arg0) {

                        List<League> leagues = ts.LeagueDate(leagueName.getText());
                        for (League league : leagues) {
                            String summonerName = league.getSummonerName();
                            int leaguePoints = league.getLeaguePoints();
                            int iconId = league.getIconId();
                            Label nameLabel = new Label(summonerName + " (" + leaguePoints + " LP)" + "icon id : " + iconId);
                            leagueF.add(nameLabel);
                            leagueF.show();
                        }
                    }
                });
                leagueF.addAll(leagueName, submitBtn);
                leagueF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new CoachingGui().showBack();
                });
                leagueF.show();
            }
        });

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
                    ), coachId)) {
                        Dialog.show("Success", "Course Inserted successfully");
                    } else {
                        Dialog.show("Failed", "Something Wrong! Try again");
                    }
                    new CoachingGui().showBack();
                });

                addF.addAll(titreTF, descriptionTF, videoTF, imageTF, prixTF, comboBox, submitBtn);
                addF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                    new CoachingGui().showBack();
                });
                addF.show();

            }
        });

        // create a new CoursesListView container
        CoursesListView listView = new CoursesListView();

        // fetch the courses
        List<Cours> courses = ts.fetchCoursesOfCoach(coachId);

        // add each course to the CoursesListView container
        for (Cours c : courses) {
            listView.addItem(c.getTitre(), c.getDescription(), c.getPrix() + " DT", c.getNiveau(), new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Form detailsF = new Form("Show Courses", new BorderLayout());

                    /**
                     * ***********************IMG ************************
                     */
                    try {
                        enc = EncodedImage.create("/load.png");
                    } catch (IOException ex) {

                    }
                    String url = Statics.urlImgCourses + c.getImage();
                    imgs = URLImage.createToStorage(enc, url, url, URLImage.RESIZE_SCALE);
                    imgv = new ImageViewer(imgs);

                    Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    content.getStyle().setPadding(Component.TOP, 10);
                    content.getStyle().setPadding(Component.BOTTOM, 10);
                    content.getStyle().setPadding(Component.LEFT, 20);
                    content.getStyle().setPadding(Component.RIGHT, 20);

                    /**
                     * ***********************Title Label
                     * ************************
                     */
                    Label titleLabel = new Label(c.getTitre());
                    titleLabel.getStyle().setFgColor(0x2D2D2D);
                    titleLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
                    content.add(titleLabel);

                    /**
                     * ***********************IMG ************************
                     */
                    Button playButton = new Button();
                    playButton.getStyle().setBgTransparency(0);
                    playButton.getStyle().setAlignment(Component.CENTER);
                    playButton.getStyle().setMargin(Component.BOTTOM, 5);
                    playButton.setMaterialIcon(FontImage.MATERIAL_PLAY_CIRCLE_FILLED);
                    playButton.addActionListener(e -> {
                        // play the video
                    });

                    Container imageContainer = new Container(new BorderLayout());
                    imageContainer.add(BorderLayout.CENTER, imgv);
                    imageContainer.add(BorderLayout.SOUTH, playButton);

                    content.add(imageContainer);

                    /**
                     * ***********************Description Label
                     * ************************
                     */
                    Label descriptionLabel = new Label(c.getDescription());
                    descriptionLabel.getStyle().setFgColor(0x7F7F7F);
                    descriptionLabel.getStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_MEDIUM));
                    descriptionLabel.setUIID("MultiLineLabel");
                    content.add(descriptionLabel);

                    detailsF.add(BorderLayout.NORTH, content);

                    detailsF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                        new CoachingGui().showBack();
                    });

                    detailsF.show();
                }

            }, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    int id = c.getId();
                    boolean result = CourseService.getInstance().deleteCourse(id);
                    if (result == true) {
                        Dialog.show("Success", "Course deleted", "OK", null);
                    } else {
                        Dialog.show("Error", "Failed to delete Course", "OK", null);
                    }
                    new CoachingGui().showBack();
                }
            }, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    Form addF = new Form("update Course", BoxLayout.y());
                    //Widgets
                    TextField titreTF = new TextField(c.getTitre(), "Cour's name");
                    TextField descriptionTF = new TextField(c.getDescription(), "Cour's description");
                    TextField videoTF = new TextField(c.getVideo(), "Cour's video");
                    TextField imageTF = new TextField(c.getImage(), "Cour's image");
                    TextField prixTF = new TextField(String.valueOf(c.getPrix()), "Cour's prix");

                    String[] items = {"debutant", "intermediare", "avance"};
                    ComboBox<String> comboBox = new ComboBox<>(items);

                    Button submitBtn = new Button("Submit");

                    //actions
                    submitBtn.addActionListener((evt) -> {
                        if (ts.updateCourse(new Cours(
                                titreTF.getText(),
                                descriptionTF.getText(),
                                videoTF.getText(),
                                imageTF.getText(),
                                Integer.parseInt(prixTF.getText()),
                                comboBox.getSelectedItem()
                        ), c.getId())) {
                            Dialog.show("Success", "Course Updated successfully", "Got it", null);
                        } else {
                            Dialog.show("Failed", "Something Wrong! Try again", "Got it", null);
                        }
                        new CoachingGui().showBack();
                    });

                    addF.addAll(titreTF, descriptionTF, videoTF, imageTF, prixTF, comboBox, submitBtn);
                    addF.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
                        new CoachingGui().showBack();
                    });
                    addF.show();

                }
            });
        }

        // create a new form and add the CoursesListView container to it
        this.addAll(addCourseBtn, lolDetails, listView);

        this.getToolbar().addMaterialCommandToLeftBar("Back", FontImage.MATERIAL_ARROW_BACK, (e) -> {
            new Acceuil().showBack();
        });
        this.show();
    }

}
