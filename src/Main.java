import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.daoimpl.LivreDaoImpl;
import com.bibliotheque.entity.Livre;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    static Connection conn;

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        LivreDao livreDao = new LivreDaoImpl();
        do {
            System.out.println(
                    """
                            ++++++++++++++++++++ Bibliotheque +++++++++++++++++++++
                            + 1.Ajouter Un Livre                                  +
                            + 2.Modifier Un Livre                                 +
                            + 3.Supprimer Un Livre                                +
                            + 4.Afficher Les Livres Disponible                    +
                            + 5.Afficher Livres Par ISBN                          +
                            + 6.Afficher Les Livres Disponible                    +
                            + 7.Afficher Les Livres Disponible                    +
                            + 8.Afficher Les Livres Disponible                    +
                            + 9.Afficher Les Livres Disponible                    +
                            + 0.Sortir                                            +
                            +++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            Entrer Votre Choix:\s""");
            Integer input = scanner.nextInt();

            switch (input) {
                case 1:
                    Livre livre = new Livre();
                    System.out.println("Entrer ISBN De Livre:");
                    String isbn = scanner.next();
                    System.out.println("Entrer Titre De Livre:");
                    String titre = scanner.next();
                    System.out.println("Entrer Auteur De Livre:");
                    String auteur = scanner.next();
                    livre.setISBN(isbn);
                    livre.setTitre(titre);
                    livre.setAuteur(auteur);
                    livre.setStatut(Livre.Statut.DISPONIBLE);
                    livreDao.ajouterLivre(livre);
                    break;
                case 2:
                    livre = new Livre();
                    System.out.println("Entrer ISBN De Livre:");
                    isbn = scanner.next();
                    System.out.println("Entrer Titre De Livre:");
                    titre = scanner.next();
                    System.out.println("Entrer Auteur De Livre:");
                    auteur = scanner.next();
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
                    break;
                case 3:
                    System.out.println("Entrer ISBN de livre: ");
                    isbn = scanner.next();
                    livreDao.supprimerLivre(isbn);
                    break;
                case 4:
                    livreDao.afficherLivre();
                    break;
                case 5:
                    System.out.println("Entrer ISBN de livre: ");
                    isbn = scanner.next();
                    livreDao.afficherLivreISBN(isbn);
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