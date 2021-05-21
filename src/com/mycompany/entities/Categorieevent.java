/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author ahlem
 */
public class Categorieevent {

    private int categoriee;
    private String namecat;
    private String descatevent;

    public Categorieevent(int categoriee, String namecat, String descatevent) {
        this.categoriee = categoriee;
        this.namecat = namecat;
        this.descatevent = descatevent;
    }

    public Categorieevent() {
    }

    public Categorieevent(String namecat, String descatevent) {
        this.namecat = namecat;
        this.descatevent = descatevent;
    }
    

    public int getCategoriee() {
        return categoriee;
    }

    public void setCategoriee(int categoriee) {
        this.categoriee = categoriee;
    }

    public String getNamecat() {
        return namecat;
    }

    public void setNamecat(String namecat) {
        this.namecat = namecat;
    }

    public String getDescatevent() {
        return descatevent;
    }

    public void setDescatevent(String descatevent) {
        this.descatevent = descatevent;
    }

}
