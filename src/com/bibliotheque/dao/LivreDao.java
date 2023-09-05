package com.bibliotheque.dao;

import com.bibliotheque.entity.Livre;

import java.sql.SQLException;

public interface LivreDao {
    public void ajouterLivre(Livre livre);
    public void afficherLivre();
    public void afficherLivreISBN(String isbn);
    public void supprimerLivre(String isbn);
    public void modifierLivre();

}
