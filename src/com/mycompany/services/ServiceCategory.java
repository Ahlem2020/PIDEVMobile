/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorieevent;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ahlem
 */
public class ServiceCategory {

    public ArrayList<Categorieevent> Categorieevents;

    public static ServiceCategory instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    public ServiceCategory() {
        req = new ConnectionRequest();

    }

    public static ServiceCategory getInstance() {
        if (instance == null) {
            instance = new ServiceCategory();
        }
        return instance;

    }

    //ajouter Categorie
    public void AddCategory(Categorieevent cat) {

        String url = Statics.BASE_URL + "/admin/categorieevent/new?categoriee=" + cat.getCategoriee() + "&namecat=" + cat.getNamecat() + "&descatevent=" + cat.getDescatevent();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });
        NetworkManager.getInstance().addToQueueAndWait(req); // execution de request  
    }

    public ArrayList<Categorieevent> parseE(String jsonText) throws IOException {

        try {

            Categorieevents = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Categorieevent C = new Categorieevent();
                         C.setCategoriee((int) Float.parseFloat(obj.get("categoriee").toString()));

                C.setNamecat(obj.get("namecat").toString());
                C.setDescatevent(obj.get("descatevent").toString());

                Categorieevents.add(C);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return Categorieevents;
    }

    public ArrayList<Categorieevent> getAllCat() {
        String url = Statics.BASE_URL + "/admin/categorieevent";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    Categorieevents = parseE(new String(req.getResponseData()));
                } catch (IOException ex) {
ex.printStackTrace();                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Categorieevents;
    }

//    //delete
    public boolean deleteevent(int id) {
        String url = Statics.BASE_URL + "/admin/categorieevent/delete/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
