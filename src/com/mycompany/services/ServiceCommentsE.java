/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.CommentsEvent;
import com.mycompany.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author ahlem
 */
public class ServiceCommentsE {
        public ArrayList<CommentsEvent> Comment;

public boolean resultOK;
    public static ServiceCommentsE instance=null;
    private ConnectionRequest req;
     float id =0;
    

    public ServiceCommentsE() {
         req = new ConnectionRequest();
    }

    public static ServiceCommentsE getInstance() {
        if (instance == null) {
            instance = new ServiceCommentsE();
        }
        return instance;
    }
    
     public boolean addComments(CommentsEvent c) {
        String url = Statics.BASE_URL + "/addCommentaire/newJSON?contenu="; //création de l'URL
        System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    }