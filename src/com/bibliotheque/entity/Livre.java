package com.bibliotheque.entity;

import java.util.List;
import java.util.Objects;

public class Livre {

    private String ISBN;
    private String titre;
    private String auteur;
    private Statut statut;
    private List<Emprunt> emprunts;

    public Livre() {
    }

    public Livre(String ISBN, String titre, String auteur, Statut statut, List<Emprunt> emprunts) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.auteur = auteur;
        this.statut = statut;
        this.emprunts = emprunts;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livre livre = (Livre) o;
        return Objects.equals(ISBN, livre.ISBN) && Objects.equals(titre, livre.titre) && Objects.equals(auteur, livre.auteur) && statut == livre.statut && Objects.equals(emprunts, livre.emprunts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ISBN, titre, auteur, statut, emprunts);
    }

    @Override
    public String toString() {
        return "Livre{" +
                "ISBN='" + ISBN + '\'' +
                ", titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", statut=" + statut +
                ", emprunts=" + emprunts +
                '}';
    }

    public enum Statut {
        PÉRDU,
        DISPONIBLE,
        EMPRUNTER,
    }
}
