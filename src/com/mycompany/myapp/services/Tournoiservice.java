/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Tournoi;
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



public class Tournoiservice {

    //var
    ConnectionRequest req;
    static Tournoiservice instance = null;

    //util
    boolean resultOK = false;
    List<Tournoi> Tournois = new ArrayList<>();

    //Constructor
    private Tournoiservice() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static Tournoiservice getInstance() {
        if (instance == null) {
            instance = new Tournoiservice();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addTournoi(Tournoi t) {

        //1
        String addURL = Statics.BASE_URL + "/addtournoiApi/new";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nb_team", t.getNb_team()+"");
        req.addArgument("nb_joueur_team", t.getNb_joueur_team()+ "");
        req.addArgument("nomtournoi", t.getNomtournoi());
        req.addArgument("device", t.getDevice());
        req.addArgument("logos", t.getImage());
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

    //FETCH
    public List<Tournoi> fetchTournois() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/tournoiapi";
        
        //2
        req.setUrl(fetchURL);
        
        //3
        req.setPost(false);
        
        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               Tournois= parseTournois(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Tournois;
    }

    //Parse
    public List<Tournoi> parseTournois(String jsonText) {

        //var
        Tournois = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> TournoisListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) TournoisListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {
                
                Tournoi t = new Tournoi();
                  float idt= Float.parseFloat( item.get("id").toString());
                t.setId((int)idt);
                float nbrteam= Float.parseFloat( item.get("nb_team").toString());
                t.setNb_team((int)nbrteam);
                float nbrplaye= Float.parseFloat( item.get("nb_joueur_team").toString());
                t.setNb_joueur_team((int)nbrplaye);
                 t.setNomtournoi((String) item.get("nomtournoi"));
                t.setDevice((String) item.get("device"));
                t.setImage((String) item.get("logos"));

                Tournois.add(t);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return Tournois;
    }

}
 