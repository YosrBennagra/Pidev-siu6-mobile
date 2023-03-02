package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.mycompany.myapp.utilities.Statics;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Jeux;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ibert
 */
public class JeuxService {

    ConnectionRequest req;
    static JeuxService instance = null;

    boolean resultOK = false;
    List<Jeux> jeux = new ArrayList<>();

    public JeuxService() {
        req = new ConnectionRequest();
    }

    public static JeuxService getInstance() {
        if (instance == null) {
            instance = new JeuxService();
        }

        return instance;
    }

    public List<Jeux> fetchJeux() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/Afficher_jeux_Json";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                jeux = parseJeux(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return jeux;
    }

   public List<Jeux> parseJeux(String jsonText) {

        //var
        jeux = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> jeuxListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) jeuxListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) { 
                Jeux j = new Jeux();
                j.setNomGame((String)item.get("nomGame"));
                j.setImage((String)item.get("image"));
                j.setDescription((String)item.get("description"));
                jeux.add(j);
            }
        } catch (IOException ex) {
        }
        
        
        
        //End
        return jeux;
    }
}