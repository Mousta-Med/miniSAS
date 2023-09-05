package com.bibliotheque.controller;

import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.daoimpl.LivreDaoImpl;
import com.bibliotheque.entity.Livre;

import java.util.List;
import java.util.Scanner;

public class LivreController {

    String isbn;
    String titre;
    String auteur;
    Livre livre = new Livre();
    Scanner scanner = new Scanner(System.in);
    LivreDao livreDao = new LivreDaoImpl();

    public void ajouterLivre(){
        System.out.println("Entrer ISBN De Livre:");
        String isbn = scanner.next();
        scanner.nextLine();
        System.out.println("Entrer Titre De Livre:");
        String titre = scanner.nextLine();
        System.out.println("Entrer Auteur De Livre:");
        String auteur = scanner.nextLine();
        livre.setISBN(isbn);
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setStatut(Livre.Statut.DISPONIBLE);
        livreDao.ajouterLivre(livre);
    }

    public void modifierLivre() {
        System.out.println("Entrer ISBN De Livre:");
        isbn = scanner.next();
        Livre result = livreDao.afficherLivreparISBN(isbn);
        if (result == null) {
            System.out.println("Aucun livre trouvé avec l'ISBN spécifié.");
        } else {
            scanner.nextLine();
            System.out.println("Entrer Titre De Livre:");
            titre = scanner.nextLine();
            System.out.println("Entrer Auteur De Livre:");
            auteur = scanner.nextLine();
            System.out.println(
                    """
                            Choisir Le nouveau Statut:
                            1.DISPONIBLE
                            2.EMPRUNTER
                            3.PÉRDU""");
            int choix = scanner.nextInt();
            livre.setISBN(isbn);
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            if (choix == 1) {
                livre.setStatut(Livre.Statut.DISPONIBLE);
                livreDao.modifierLivre(livre);
            } else if (choix == 2) {
                livre.setStatut(Livre.Statut.EMPRUNTER);
                livreDao.modifierLivre(livre);
            } else if (choix == 3) {
                livre.setStatut(Livre.Statut.PÉRDU);
                livreDao.modifierLivre(livre);
            } else {
                System.out.println("Votre Choix Est Invalide");
            }
        }
    }
    public void supprimerLivre(){
        System.out.println("Entrer ISBN de livre: ");
        isbn = scanner.next();
        livreDao.supprimerLivre(isbn);
    }
    public void chercherLivre(){
        System.out.println("Entrer Titre ou Auteur de livre: ");
        titre = scanner.next();
        List<Livre> result = livreDao.chercherLivre(titre);
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur");
        System.out.println("-------------------------------------------------------");
        if (!result.isEmpty()) {
            for (Livre livre1 : result){
                System.out.printf("| %-10s | %-20s | %-15s |\n",
                        livre1.getISBN(),
                        livre1.getTitre(),
                        livre1.getAuteur());
            }
        } else {
            System.out.printf("| %-51s |\n", "il n'y a pas de livre avec cette titre");
        }
        System.out.println("-------------------------------------------------------");
    }
}
