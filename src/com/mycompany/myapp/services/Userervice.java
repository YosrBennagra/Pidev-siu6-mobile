/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.gui.SessionManager;
import com.mycompany.myapp.utilities.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nejdb
 */
public class Userervice {
    String result=" ";
     ConnectionRequest req;
     boolean resultOK = false;
       static Userervice instance = null;
        List<String> l=new ArrayList<>();
      public Userervice() {
        req = new ConnectionRequest();
    }

    public static Userervice getInstance() {
        if (instance == null) {
            instance = new Userervice();
        }

        return instance;
    }
    
    
    public String login(String email,String password) {

        //1
        String addURL = Statics.BASE_URL + "/loginmobile/"+email+"/"+password;
        
        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String data;
                data=new String(req.getResponseData());
                result=getResponse(data);
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
    
    
    
    
    public  List<String> signupgamer(String tag,String nom,String prenom,String phone,Date date,String about,String email,String password) {

        //1
        String addURL = Statics.BASE_URL + "/signupgamer/"+tag+"/"+nom+"/"+prenom+"/"+phone+"/"+date+"/"+about+"/"+email+"/"+password;
        
        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
       
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String data;
                data=new String(req.getResponseData());
                l=getResult(data);
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return l;
    }
    
    
    public String getuser(Integer id) {

        //1
        String addURL = Statics.BASE_URL + "/getuser/"+id;
        
        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String data;
                data=new String(req.getResponseData());
                result=getResponse(data);
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
    
    
    
    public String sendmail(String mail) {

        //1
        String addURL = Statics.BASE_URL + "/sendmail2/"+mail;
        
        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        
        //5
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                String data;
                data=new String(req.getResponseData());
                result=getResponse(data);
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

        return result;
    }
    
    
    
    public String getResponse(String jsonText){
        JSONParser jp = new JSONParser();
        String result="null";
        
        try {
            
            //2
            Map<String, Object> ListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            String code;
              code = (String) ListJSON.get("status");
              result=(String) ListJSON.get("message");
              String role=(String) ListJSON.get("role");
              if("200".equals(code)){
                SessionManager.setId((Double) ListJSON.get("id"));
                SessionManager.setNom((String) ListJSON.get("nom"));
                SessionManager.setPrenom((String) ListJSON.get("prenom"));
                if("gamer".equals(role)){
                     SessionManager.setTag((String) ListJSON.get("tag"));
                }
                SessionManager.setEmail((String) ListJSON.get("email"));
                SessionManager.setAbout((String) ListJSON.get("about"));
                SessionManager.setSolde((Double) ListJSON.get("solde"));
                SessionManager.setPhoto((String) ListJSON.get("photo"));
                SessionManager.setRole((String) ListJSON.get("role"));
                
              }
   } catch (IOException ex) {
        }
        return result;
    }
    
    
    
    
    public List getResult(String jsonText){
        JSONParser jp = new JSONParser();
        String result="null";
        String code="404";
        List<String> l=new ArrayList<>();
        
        try {
            //2
            Map<String, Object> ListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
              code = (String) ListJSON.get("status");
              result=(String) ListJSON.get("message");
              l.add(code);
              l.add(result);
   } catch (IOException ex) {
        }
        return l;
    }
    
    
    
}
