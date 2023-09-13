package com.bibliotheque.controller;

import com.bibliotheque.dao.EmpruntDao;
import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.dao.MemberDao;
import com.bibliotheque.daoimpl.EmpruntDaoImpl;
import com.bibliotheque.daoimpl.LivreDaoImpl;
import com.bibliotheque.daoimpl.MemberDaoImpl;
import com.bibliotheque.entity.Emprunt;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Membre;
import java.util.Calendar;
import java.sql.Date;
import java.util.Scanner;

public class EmpruntController {

    Scanner scanner = new Scanner(System.in);
    EmpruntDao empruntDao = new EmpruntDaoImpl();
    MemberController memberController = new MemberController();
    LivreDao livreDao = new LivreDaoImpl();
    MemberDao memberDao = new MemberDaoImpl();
    Emprunt emprunt = new Emprunt();
    Date date = new Date(System.currentTimeMillis());
    Integer némuro;

    public void ajouterEmprunt() {
        System.out.println("L'Empranteur est:");
        System.out.println("1:Déja Member");
        System.out.println("2:Nouveau Member");
        if (scanner.hasNextInt()) {
            Integer role = scanner.nextInt();
            scanner.nextLine();
            if (role == 1) {
                System.out.println("Entrer Némuro De Member:");
                némuro = scanner.nextInt();
                this.fun();
            } else if (role == 2) {
                némuro = memberController.ajouterMember();
                this.fun();
            } else {
                System.out.println("Invalid Choix !!!");
            }
        } else {
            System.out.println("Invalid Choix !!!");
        }
    }

    public void fun() {
        System.out.println("Entrer ISBN De Livre:");
        String Isbn = scanner.next();
        System.out.println("Entrer La Durée D'emprunt");
        Integer duree = scanner.nextInt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, duree);
        Date date1 = new Date(calendar.getTimeInMillis());
        Membre membre = memberDao.chercherMember(némuro);
        Livre livre = livreDao.afficherLivreparISBN(Isbn);
        if (livre.getStatut() == Livre.Statut.DISPONIBLE) {
            if (membre != null && livre != null) {
                emprunt.setDate_fin_demprunt(date1);
                emprunt.setDate_demprunt(date);
                emprunt.setLivre(livre);
                emprunt.setMembre(membre);
                empruntDao.ajouterEmprunt(emprunt);
                System.out.println("le Livre A été Emprunter");
            } else {
                System.out.println("ISBN OU Némuro DE MEMBER EST Invalid");
            }
        } else {
            System.out.println("Le livre Déja Emprunter");
        }
    }

    public void retournerEmprunt() {
        System.out.println("Entrer ISBN de livre");
        String isbn = scanner.nextLine();
        Boolean result = empruntDao.supprimerEmprunt(isbn);
        if (result == true) {
            System.out.println("le Livre A été Retourner");
        } else {
            System.out.println("le Livre Ne Pas Retourner");

        }
    }
}
