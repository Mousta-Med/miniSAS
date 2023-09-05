package com.bibliotheque.entity;

import java.sql.Date;

public class Emprunt {
    private Livre livre;
    private Membre membre;
    private Date date_demprunt;
    private Date date_fin_demprunt;

    public Emprunt(Livre livre, Membre membre, Date date_demprunt, Date date_fin_demprunt) {
        this.livre = livre;
        this.membre = membre;
        this.date_demprunt = date_demprunt;
        this.date_fin_demprunt = date_fin_demprunt;
    }

    public Emprunt() {
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Date getDate_demprunt() {
        return date_demprunt;
    }

    public void setDate_demprunt(Date date_demprunt) {
        this.date_demprunt = date_demprunt;
    }

    public Date getDate_fin_demprunt() {
        return date_fin_demprunt;
    }

    public void setDate_fin_demprunt(Date date_fin_demprunt) {
        this.date_fin_demprunt = date_fin_demprunt;
    }
}
