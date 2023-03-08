/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.CommentaireNews;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ibert
 */
public class CommentaireNewsService {
            ConnectionRequest req;
    static CommentaireNewsService instance = null;

    boolean resultOK = false;
    List<CommentaireNews> commentairenews = new ArrayList<>();

    public CommentaireNewsService() {
        req = new ConnectionRequest();
    }

    public static CommentaireNewsService getInstance() {
        if (instance == null) {
            instance = new CommentaireNewsService();
        }

        return instance;
    }
    //add
        public boolean addCommentaireNews(CommentaireNews cn) {

        //1
        String addURL = Statics.BASE_URL + "/Ajouter_Commentaire_Json";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("description", cn.getDescription() + "");
        req.addArgument("user", 1 + "");
        req.addArgument("idNews", cn.getIdNews() + "");

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
        

    public List<CommentaireNews> fetchCommentaireNews(int id) {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/Rechercher_comments_news_Json/"+id;

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentairenews = parseCommentaireNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return commentairenews;
    }

   public List<CommentaireNews> parseCommentaireNews(String jsonText) {

        //var
        commentairenews = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> commentairenewsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) commentairenewsListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {            
                CommentaireNews n = new CommentaireNews();
                Map<String, Object> user = (Map<String, Object>) item.get("user");
                n.setUsername((String) user.get("nom"));
                n.setDescription((String)item.get("description"));
                commentairenews.add(n);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return commentairenews;
    }
    
}
