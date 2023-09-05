package com.bibliotheque.entity;

public class Livre {

    private String ISBN;
    private String titre;
    private String auteur;
    private Statut statut;

    public Livre() {
    }

    public Livre(String ISBN, String titre, String auteur, Statut statut) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.auteur = auteur;
        this.statut = statut;
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

    public enum Statut {
        PÉRDU,
        DISPONIBLE,
        EMPRUNTER,
    }
}
