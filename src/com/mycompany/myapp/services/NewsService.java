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
import com.mycompany.myapp.entities.News;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ibert
 */
public class NewsService {
        ConnectionRequest req;
    static NewsService instance = null;

    boolean resultOK = false;
    List<News> news = new ArrayList<>();

    public NewsService() {
        req = new ConnectionRequest();
    }

    public static NewsService getInstance() {
        if (instance == null) {
            instance = new NewsService();
        }

        return instance;
    }

    public List<News> fetchNews() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/Afficher_news_Json";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                news = parseNews(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return news;
    }

   public List<News> parseNews(String jsonText) {

        //var
        news = new ArrayList<>();
        
        //DO
        //1
        JSONParser jp = new JSONParser();
        
        try {
            
            //2
            Map<String, Object> newsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        
            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) newsListJSON.get("root");
        
            //4
            for (Map<String, Object> item : list) {            
                News n = new News();
                n.setDescription((String)item.get("description"));
                

                
                news.add(n);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return news;
    }
    
}
