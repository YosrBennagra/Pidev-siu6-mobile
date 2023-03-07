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
import com.codename1.io.FileSystemStorage;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.League;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jallouli
 */
public class CourseService {

    private String result;

    //var
    ConnectionRequest req;
    static CourseService instance = null;

    //util
    boolean resultOK = false;
    List<Cours> courses = new ArrayList<>();
    List<Cours> Coachcourses = new ArrayList<>();

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
    public boolean addCourse(Cours c, int idCoach) {

        //1
        String addURL = Statics.BASE_URL + "/coaching/addCAPI/new" + idCoach;

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

    public boolean updateCourse(Cours c, int id) {

        //1
        String addURL = Statics.BASE_URL + "/Course/updateAPI/" + id;

        //2
        req.setUrl(addURL);

        req.setPost(false);

        req.addArgument("titre", c.getTitre());
        req.addArgument("description", c.getDescription());
        req.addArgument("video", c.getVideo());
        req.addArgument("image", c.getImage());
        req.addArgument("prix", c.getPrix() + "");
        req.addArgument("niveau", c.getNiveau());
        //5
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
                courses = parseCourses(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return courses;
    }

    //Parse
    public List<Cours> parseCourses(String jsonText) {

        //var
        courses = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> coursesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) coursesListJSON.get("root");

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

                courses.add(t);
            }

        } catch (IOException ex) {
        }

        //End
        return courses;
    }

    public List<League> parseLeague(String jsonText) {
        List<League> leagues = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> leagueJSON = parser.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            Map<String, Object> leagueInfoJSON = (Map<String, Object>) leagueJSON.get("leagueInfo");
            String summonerName = (String) leagueInfoJSON.get("summonerName");
            String tier = (String) leagueInfoJSON.get("tier");
            int leaguePoints = ((Double) leagueInfoJSON.get("leaguePoints")).intValue();
            int wins = ((Double) leagueInfoJSON.get("wins")).intValue();
            int losses = ((Double) leagueInfoJSON.get("losses")).intValue();
            int iconId = ((Double) leagueJSON.get("iconId")).intValue();
            League league = new League(summonerName, tier, leaguePoints, wins, losses, iconId);
            leagues.add(league);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return leagues;
    }

    public List<League> LeagueDate(String name) {
        String addURL = Statics.BASE_URL + "/summonerApi";
        req.setUrl(addURL);
        req.setPost(false);
        req.addArgument("name", name);
        List<League> leagues = new ArrayList<>();
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                leagues.addAll(parseLeague(new String(req.getResponseData())));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return leagues;
    }

    //FETCH
    public List<Cours> fetchCoursesOfCoach(int id) {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/coachApi/" + id + "/courses";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Coachcourses = parseCoursesOfCoach(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return Coachcourses;
    }

    //Parse
    public List<Cours> parseCoursesOfCoach(String jsonText) {

        //var
        Coachcourses = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> coursesListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) coursesListJSON.get("root");

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

                Coachcourses.add(t);
            }

        } catch (IOException ex) {
        }

        //End
        return Coachcourses;
    }

}
