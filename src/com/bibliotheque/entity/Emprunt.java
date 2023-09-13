package com.bibliotheque.entity;

import java.sql.Date;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Emprunt emprunt = (Emprunt) o;
        return Objects.equals(livre, emprunt.livre) && Objects.equals(membre, emprunt.membre) && Objects.equals(date_demprunt, emprunt.date_demprunt) && Objects.equals(date_fin_demprunt, emprunt.date_fin_demprunt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livre, membre, date_demprunt, date_fin_demprunt);
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "livre=" + livre +
                ", membre=" + membre +
                ", date_demprunt=" + date_demprunt +
                ", date_fin_demprunt=" + date_fin_demprunt +
                '}';
    }
}
