import com.bibliotheque.controller.EmpruntController;
import com.bibliotheque.controller.LivreController;
import com.bibliotheque.controller.MemberController;
import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.daoimpl.LivreDaoImpl;
import com.bibliotheque.entity.Emprunt;
import com.bibliotheque.entity.Livre;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Connection conn;

    public static void main(String[] args) throws SQLException {
        MemberController memberController = new MemberController();
        LivreController livreController = new LivreController();
        EmpruntController empruntController = new EmpruntController();
        Scanner scanner = new Scanner(System.in);
        livreController.updateperdulivre();
        do {
            System.out.println(
                    """
                            ++++++++++++++++++++ Bibliotheque +++++++++++++++++++++
                            + 1.Ajouter Un Livre                                  +
                            + 2.Modifier Un Livre                                 +
                            + 3.Supprimer Un Livre                                +
                            + 4.Afficher Les Livres Disponible                    +
                            + 5.Chercher Un Livre Par Titre ou Auteur             +
                            + 6.Ajouter Un Member                                 +
                            + 7.Modifier Un Member                                +
                            + 8.Supprimer Un Member                               +
                            + 9.Emprunter Un Livre                                +
                            + 10.Afficher Les Members                             +
                            + 11.Afficher Les Livres Emprunter                    +
                            + 12.Retourner Un Livre Emprunter                     +
                            + 13.Aficher Statistique                              +
                            + 14.Exporter Statistique                             +
                            + 0.Sortir                                            +
                            +++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            Entrer Votre Choix:\s""");
            Integer input = scanner.nextInt();
            switch (input) {
                case 1:
                    livreController.ajouterLivre();
                    break;
                case 2:
                    livreController.modifierLivre();
                    break;
                case 3:
                    livreController.supprimerLivre();
                    break;
                case 4:
                    livreController.afficherLivre(4);
                    break;
                case 5:
                    livreController.chercherLivre();
                    break;
                case 6:
                    memberController.ajouterMember();
                    break;
                case 7:
                    memberController.modifierMember();
                    break;
                case 8:
                    memberController.supprimerMember();
                    break;
                case 9:
                    empruntController.ajouterEmprunt();
                    break;
                case 10:
                    memberController.afficherMembers();
                    break;
                case 11:
                    livreController.afficherLivre(11);
                    break;
                case 12:
                    empruntController.retournerEmprunt();
                    break;
                case 13:
                    livreController.getStats();
                    break;
                case 14:
                    livreController.exportstats();
                    break;
                case 0:
                    System.out.println("Merci...");
                    System.exit(0);
                default:
                    System.out.println("Votre Choix Est Invalide");
            }

        } while (true);

    }
}