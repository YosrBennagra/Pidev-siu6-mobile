package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Produit;
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


public class ProduitService {

    //var
    ConnectionRequest req;
    static ProduitService instance = null;

    //util
    boolean resultOK = false;
    List<Produit> Produits = new ArrayList<>();

    //Constructor
    private ProduitService() {
        req = new ConnectionRequest();
    }

    //Singleton
    public static ProduitService getInstance() {
        if (instance == null) {
            instance = new ProduitService();
        }

        return instance;
    }

    //ACTIONS
    //Add
    public boolean addProduit(Produit t) {

        //1
        String addURL = Statics.BASE_URL + "/Ajouter_produit_Json";

        //2
        req.setUrl(addURL);

        //3
        req.setPost(false);

        //4
        req.addArgument("nom", t.getNom());
        req.addArgument("prix", t.getPrix() + "");
        req.addArgument("description", t.getDescription());
        req.addArgument("image", t.getImage());

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

    //DELETE
    public boolean deleteProduit(int id) {

        //1
        String deleteURL = Statics.BASE_URL + "/supprimerproduit/" + id;

        //2
        req.setUrl(deleteURL);

        //3
        req.setHttpMethod("DELETE");

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
    public List<Produit> fetchProduits() {

        req = new ConnectionRequest();

        //1
        String fetchURL = Statics.BASE_URL + "/Afficher_produit_Json";

        //2
        req.setUrl(fetchURL);

        //3
        req.setPost(false);

        //4
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produits = parseProduits(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produits;
    }

    //Parse
    public List<Produit> parseProduits(String jsonText) {

        //var
        Produits = new ArrayList<>();

        //DO
        //1
        JSONParser jp = new JSONParser();

        try {

            //2
            Map<String, Object> ProduitsListJSON = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            //3
            List<Map<String, Object>> list = (List<Map<String, Object>>) ProduitsListJSON.get("root");

            //4
            for (Map<String, Object> item : list) {

                Produit t = new Produit();
                float id = Float.parseFloat(item.get("id").toString());
                float prix = Float.parseFloat(item.get("prix").toString());
                t.setId((int) id);
                t.setNom((String) item.get("nom"));
                t.setPrix((int) prix);
                t.setDescription((String) item.get("description"));
                t.setImage((String) item.get("image"));
                Produits.add(t);
            }

        } catch (IOException ex) {
        }

        //End
        return Produits;
    }

}
