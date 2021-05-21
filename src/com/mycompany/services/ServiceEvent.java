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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Categorieevent;
import com.mycompany.entities.Event;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author ahlem
 */
public class ServiceEvent {

    public ArrayList<Event> Events;

    public static ServiceEvent instance = null;
    public boolean resultOK = true;
    private ConnectionRequest req;

    private ServiceEvent() {
        req = new ConnectionRequest();
    }

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public boolean AddEvent(Event E) {
        String url = Statics.BASE_URL + "/admin/new?nomEvent=" + E.getNomEvent() + "&descriptionEvent=" + E.getDescriptionEvent() + "&isactive=" + E.getIsactive() + "&dateDebEvent=" + E.getDateDebEvent() + "&dateFinEvent=" + E.getDateFinEvent() + "&imageevent=" + E.getImageevent() + "&nbrParticipant=" + E.getNbrParticipant();
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("Event == " + str);

        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Event> parseE(String jsonText) {

        try {

            Events = new ArrayList<>();

            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                Event E = new Event();
                Categorieevent m = new Categorieevent();
                E.setNomEvent(obj.get("nomEvent").toString());
                E.setDescriptionEvent(obj.get("descriptionEvent").toString());
                E.setIsactive(Integer.parseInt(obj.get("isactive").toString()));
                String DateD = obj.get("dateDebEvent").toString();
                String DateF = obj.get("dateDebEvent").toString();

                SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                String DateDeb = formater.format(DateD);

                String DateFin = formater.format(DateF);
                E.setDateDebEvent(formater.format(DateDeb));
                E.setDateFinEvent(formater.format(DateFin));
                E.setImageevent("60882c01c79b6.jpeg");
                E.setNbrParticipant((int) Float.parseFloat(obj.get("nbrParticipant").toString()));

                //   E.setCategoriee(Integer.parseInt(obj.get("categorieE").toString()));
                Events.add(E);
            }

        } catch (IOException ex) {
            System.out.println(ex);
        }

        return Events;
    }

    public ArrayList<Event> getAllEvent() {
        String url = Statics.BASE_URL + "/admin/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                Events = parseE(new String(req.getResponseData()));

                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Events;
    }

    //delete
    public boolean deleteevent(int id) {
        String url = Statics.BASE_URL + "/admin/delete/" + id;
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
    public void Participate(int id) {
           String url = Statics.BASE_URL + "/admin/Participer/"+id;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("Event == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        //return resultOK;
        
        }
        
      
        
    }
 