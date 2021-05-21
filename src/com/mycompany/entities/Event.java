/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author ahlem
 */
public class Event {
    private int IdEvent;
    private String nomEvent;
    private String descriptionEvent;
    private int isactive;
    private String dateDebEvent;
    private String dateFinEvent;
    private String imageevent;
    private int nbrParticipant;
    private Categorieevent categoriee;

    public Event() {
    }

    public Event(String nomEvent, String descriptionEvent, int isactive, String dateDebEvent, String dateFinEvent, String imageevent, int nbrParticipant, Categorieevent categoriee) {
        this.nomEvent = nomEvent;
        this.descriptionEvent = descriptionEvent;
        this.isactive = isactive;
        this.dateDebEvent = dateDebEvent;
        this.dateFinEvent = dateFinEvent;
        this.imageevent = imageevent;
        this.nbrParticipant = nbrParticipant;
        this.categoriee = categoriee;
    }

    public Event(int IdEvent, String nomEvent, String descriptionEvent, int isactive, String dateDebEvent, String dateFinEvent, String imageevent, int nbrParticipant, Categorieevent categoriee) {
        this.IdEvent = IdEvent;
        this.nomEvent = nomEvent;
        this.descriptionEvent = descriptionEvent;
        this.isactive = isactive;
        this.dateDebEvent = dateDebEvent;        this.descriptionEvent = descriptionEvent;

        this.dateFinEvent = dateFinEvent;
        this.imageevent = imageevent;
        this.nbrParticipant = nbrParticipant;
        this.categoriee = categoriee;
    }

    public Event(String nomEvent, String descriptionEvent, String dateDebEvent, String dateFinEvent) {
        this.nomEvent = nomEvent;
        this.descriptionEvent = descriptionEvent;
        this.dateDebEvent = dateDebEvent;
        this.dateFinEvent = dateFinEvent;
    }

    public Event(String nomEvent, String descriptionEvent, int isactive, String dateDebEvent, String dateFinEvent, String imageevent, int nbrParticipant) {
        this.nomEvent = nomEvent;
        this.descriptionEvent = descriptionEvent;
        this.isactive = isactive;
        this.dateDebEvent = dateDebEvent;
        this.dateFinEvent = dateFinEvent;
        this.imageevent = imageevent;
        this.nbrParticipant = nbrParticipant;
    }







     

    public int getIdEvent() {
        return IdEvent;
    }

    public void setIdEvent(int IdEvent) {
        this.IdEvent = IdEvent;
    }

    public String getNomEvent() {
        return nomEvent;
    }

    public void setNomEvent(String nomEvent) {
        this.nomEvent = nomEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }

    public int getIsactive() {
        return isactive;
    }

    public void setIsactive(int isactive) {
        this.isactive = isactive;
    }

    public String getDateDebEvent() {
        return dateDebEvent;
    }

    public void setDateDebEvent(String dateDebEvent) {
        this.dateDebEvent = dateDebEvent;
    }

    public String getDateFinEvent() {
        return dateFinEvent;
    }

    public void setDateFinEvent(String dateFinEvent) {
        this.dateFinEvent = dateFinEvent;
    }

    public String getImageevent() {
        return imageevent;
    }

    public void setImageevent(String imageevent) {
        this.imageevent = imageevent;
    }

    public int getNbrParticipant() {
        return nbrParticipant;
    }

    public void setNbrParticipant(int nbrParticipant) {
        this.nbrParticipant = nbrParticipant;
    }

    public Categorieevent getCategoriee() {
        return categoriee;
    }

    public void setCategoriee(Categorieevent categoriee) {
        this.categoriee = categoriee;
    }
    
    

    
    
    
    
}
