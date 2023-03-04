/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Cours;
import com.mycompany.myapp.utilities.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jallouli
 */
public class CourseService {

    //var
    ConnectionRequest req;
    static CourseService instance = null;

    //util
    boolean resultOK = false;
    List<Cours> tasks = new ArrayList<>();

    //Constructor
    private CourseService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static CourseService getInstance() {
        if (instance == null) {
            instance = new CourseService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addCourse(Cours c) {

        //1
        String addURL = Statics.BASE_URL + "/coaching/addCAPI/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);
        req.addArgument("titre", c.getTitre());
        req.addArgument("description", c.getDescription());
        req.addArgument("video", c.getVideo());
        req.addArgument("image", c.getImage());
        req.addArgument("prix", c.getPrix() + "");
        req.addArgument("niveau", c.getNiveau());
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return resultOK;
    }

    public boolean deleteCourse(int id) {

        //1
        String deleteURL = Statics.BASE_URL + "/Course/DeleteAPI/" + id;

        //2
        req.setUrl(deleteURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    //FETCH
    public List<Cours> fetchCourses() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/coaching/allCoursesAPI";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }

    //Parse
    public List<Cours> parseTasks(String jsonText) {

        //var
        tasks = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> tasksListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Cours t = new Cours();

                float prix = Float.parseFloat(item.get("prix").toString());
                float id = Float.parseFloat(item.get("id").toString());
                t.setId((int) id);

                t.setTitre((String) item.get("titre"));
                t.setDescription((String) item.get("description"));
                t.setVideo((String) item.get("video"));
                t.setImage((String) item.get("image"));
                t.setPrix((int) prix);
                t.setNiveau((String) item.get("niveau"));

                tasks.add(t);
            }

        } catch (IOException ex) {
        }

        //End
        return tasks;
    }
}
