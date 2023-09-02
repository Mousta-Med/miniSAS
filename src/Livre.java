public class Livre {

    private String ISBN;
    private String titre;
    private String auteur;
    private enum Statut {
        PÉRDU,
        DISPONIBLE,
        EMPRUNTER,
    }
}
