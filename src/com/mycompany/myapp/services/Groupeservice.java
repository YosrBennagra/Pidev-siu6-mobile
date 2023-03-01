
package com.mycompany.myapp.services;



import com.mycompany.myapp.utilities.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Groupe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author mehdi hanini
 */
public class Groupeservice {

    //var
    ConnectionRequest req;
    static Groupeservice instance = null;

    //util
    boolean resultOK = false;
    List<Groupe> tasks = new ArrayList<>();

    //Constructor
    private Groupeservice() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static Groupeservice getInstance() {
        if (instance == null) {
            instance = new Groupeservice();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addTask(Groupe t) {

        //1
        String addURL = Statics.BASE_URL + "/ajoutmobile";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("Nom_groupe", t.getNom_groupe());
        req.addArgument("image", t.getImage());
        req.addArgument("description", t.getDescription());
    

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
    public List<Groupe> fetchGroupe() {
        
        req = new ConnectionRequest();
        
        //1
        String fetchURL = Statics.BASE_URL + "/groupemobile";
        
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
    public List<Groupe> parseTasks(String jsonText) {

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
                
                Groupe t = new Groupe();
                 
float idt= Float.parseFloat(item.get("id").toString());
                 t.setId((int)idt);

                 t.setNom_groupe((String) item.get("Nom_groupe"));
                t.setImage((String) item.get("image"));
                t.setDescription((String) item.get("description"));
                float idowner= Float.parseFloat(item.get("idOwner").toString());
                 t.setIdOwner((int)idowner);

 float etatt= Float.parseFloat(item.get("etat").toString());
                 t.setEtat((int)etatt);
                

                
               

                
                
                tasks.add(t);
            }
        
        } catch (IOException ex) {
        }
        
        
        
        //End
        return tasks;
    }

}
