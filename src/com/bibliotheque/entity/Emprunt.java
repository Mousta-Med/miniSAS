package com.bibliotheque.entity;

import java.util.Date;

public class Emprunt {

    private Livre livre;
    private Membre membre;
    private Date date_demprunt;
    private Integer durée_demprunt;
    public Date getDate_demprunt() {
        return date_demprunt;
    }
    public void setDate_demprunt(Date date_demprunt) {
        this.date_demprunt = date_demprunt;
    }
    public Integer getDurée_demprunt() {
        return durée_demprunt;
    }
    public void setDurée_demprunt(Integer durée_demprunt) {
        this.durée_demprunt = durée_demprunt;
    }
    public Livre getLivre() {return livre;}
    public void setLivre(Livre livre) {this.livre = livre;}
    public Membre getMembre() {return membre;}
    public void setMembre(Membre membre) {this.membre = membre;}
}
