package com.bibliotheque.controller;

import com.bibliotheque.dao.EmpruntDao;
import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.daoimpl.EmpruntDaoImpl;
import com.bibliotheque.daoimpl.LivreDaoImpl;
import com.bibliotheque.entity.Emprunt;
import com.bibliotheque.entity.Livre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class LivreController {

    String isbn;
    String titre;
    String auteur;
    Livre livre = new Livre();
    Scanner scanner = new Scanner(System.in);
    LivreDao livreDao = new LivreDaoImpl();

    public void ajouterLivre() {
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
        if (livreDao.ajouterLivre(livre) != null) {
            System.out.println("Livre A été Bien Ajouter");
        } else {
            System.out.println("Livre ne pas Bien Ajouter");
        }
    }

    public void afficherLivre() {
        List<Livre> result;
        result = livreDao.afficherLivre();
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur");
        System.out.println("-------------------------------------------------------");
        if (!result.isEmpty()) {
            for (Livre livre1 : result) {
                System.out.printf("| %-10s | %-20s | %-15s |\n",
                        livre1.getISBN(),
                        livre1.getTitre(),
                        livre1.getAuteur());
            }
        } else {
            System.out.printf("| %-51s |\n", "il n'y a pas de livre");
        }
        System.out.println("-------------------------------------------------------");
    }

    public void afficherLivreEmprunter() {
        List<Livre> result;
        result = livreDao.afficherLivreEmprunter();
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur", "Emprunteur", "Date D'emprunt");
        System.out.println("------------------------------------------------------------------------------------------------");
        if (!result.isEmpty()) {
            for (Livre livre1 : result) {
                EmpruntDao empruntDao = new EmpruntDaoImpl();
                Emprunt emprunt = empruntDao.chercheEmprunt(livre1.getISBN());
                System.out.printf("| %-10s | %-20s | %-15s | %-20s | %-15s |\n",
                        emprunt.getLivre().getISBN(),
                        emprunt.getLivre().getTitre(),
                        emprunt.getLivre().getAuteur(),
                        emprunt.getMembre().getMemberNom(),
                        emprunt.getDate_demprunt());
            }
        } else {
            System.out.printf("| %-92s |\n", "il n'y a pas de livre");
        }
        System.out.println("------------------------------------------------------------------------------------------------");
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
            livre.setISBN(isbn);
            livre.setTitre(titre);
            livre.setAuteur(auteur);
            livreDao.modifierLivre(livre);
            System.out.println("Livre a été bien modifier:");
        }
    }

    public void supprimerLivre() {
        System.out.println("Entrer ISBN de livre: ");
        isbn = scanner.next();
        livreDao.supprimerLivre(isbn);
    }

    public void chercherLivre() {
        System.out.println("Entrer Titre ou Auteur de livre: ");
        titre = scanner.next();
        List<Livre> result = livreDao.chercherLivre(titre);
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur");
        System.out.println("-------------------------------------------------------");
        if (!result.isEmpty()) {
            for (Livre livre1 : result) {
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

    public void getStats() throws SQLException {
        ResultSet resultSet = livreDao.livreStatistique();
        System.out.println("---------------------------");
        System.out.printf("| %-10s | %-10s |\n", "Stats", "Count");
        System.out.println("---------------------------");
        while (resultSet.next()) {
            System.out.printf("| %-10s | %-10s |\n",
                    resultSet.getString("statut"),
                    resultSet.getInt("count"));
        }
        System.out.println("---------------------------");
    }

    public void exportstats() {
        ResultSet resultSet = livreDao.livreStatistique();
        livreDao.createFile(resultSet);
    }

    public void updateperdulivre() {
        livreDao.updatePérduLivre();
    }
}
