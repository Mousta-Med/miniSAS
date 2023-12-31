package com.bibliotheque.controller;

import com.bibliotheque.dao.MemberDao;
import com.bibliotheque.daoimpl.MemberDaoImpl;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Membre;

import java.util.List;
import java.util.Scanner;

public class MemberController {
    MemberDao memberDao = new MemberDaoImpl();
    Membre membre = new Membre();
    Scanner scanner = new Scanner(System.in);

    public Integer ajouterMember() {
        System.out.println("Entrer Member Némuro:");
        if (scanner.hasNextInt()) {
            Integer némuro = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Entrer Member Nom:");
            String nom = scanner.nextLine();
            System.out.println("Entrer Telephone De Member:");
            if (scanner.hasNextInt()) {
                Integer telephone = scanner.nextInt();
                System.out.println("Entrer CIN De Member:");
                String cin = scanner.next();
                membre.setMemberNémuro(némuro);
                membre.setMemberNom(nom);
                membre.setMemberTelephon(telephone);
                membre.setMemberCin(cin);
                if (memberDao.ajouterMember(membre) != null) {
                    System.out.println("Member A été Bien Ajouter");
                    return némuro;
                } else {
                    System.out.println("Member ne pas Bien Ajouter");
                }
            } else {
                System.out.println("Invalid input for Telephone De Member.");
            }
        } else {
            System.out.println("Invalid input for Member Némuro.");
        }
        return null;
    }

    public void modifierMember(){
        System.out.println("Entrer Member Némuro:");
        if (scanner.hasNextInt()) {
            Integer némuro = scanner.nextInt();
            Membre result = memberDao.chercherMember(némuro);
            if (result == null){
                System.out.println("Aucun Member trouvé avec némuro spécifié.");
            }else {
                scanner.nextLine();
                System.out.println("Entrer Member Nom:");
                String nom = scanner.nextLine();
                System.out.println("Entrer Telephone De Member:");
                if (scanner.hasNextInt()) {
                    Integer telephone = scanner.nextInt();
                    System.out.println("Entrer CIN De Member:");
                    String cin = scanner.next();
                    membre.setMemberNémuro(némuro);
                    membre.setMemberNom(nom);
                    membre.setMemberTelephon(telephone);
                    membre.setMemberCin(cin);
                    if (memberDao.modifierMember(membre) != null) {
                        System.out.println("Member A été Bien Modifier");
                    } else {
                        System.out.println("Member ne pas Bien Modifier");
                    }
                } else {
                    System.out.println("Invalid input for Telephone De Member.");
                }
            }

        } else {
            System.out.println("Invalid input for Member Némuro.");
        }
    }

    public void supprimerMember(){
        System.out.println("Entrer Member Némuro:");
        if (scanner.hasNextInt()) {
            Integer némuro = scanner.nextInt();
            Membre result = memberDao.chercherMember(némuro);
            if (result == null){
                System.out.println("Aucun Member trouvé avec némuro spécifié.");
            }else {
                memberDao.supprimerMember(némuro);
                System.out.println("Member A été Bien Supprimer");
            }
        }
    }

    public void afficherMembers() {
        List<Membre> result;
        result = memberDao.afficherMembers();
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("| %-10s | %-20s | %-15s | %-10s |\n", "Némuro", "Nom", "Tele", "Cin");
        System.out.println("--------------------------------------------------------------------");
        if (!result.isEmpty()) {
            for (Membre membre1 : result) {
                System.out.printf("| %-10s | %-20s | %-15s | %-10s |\n",
                        membre1.getMemberNémuro(),
                        membre1.getMemberNom(),
                        "0" + membre1.getMemberTelephon(),
                        membre1.getMemberCin());
            }
        } else {
            System.out.printf("| %-51s |\n", "il n'y a pas de Member Ajouter");
        }
        System.out.println("--------------------------------------------------------------------");
    }
}
