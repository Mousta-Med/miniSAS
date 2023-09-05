package com.bibliotheque.dao;

import com.bibliotheque.entity.Livre;

import java.util.List;

public interface LivreDao {
    public void ajouterLivre(Livre livre);

    public void afficherLivre();

    public Livre afficherLivreISBN(String isbn);

    public Livre afficherLivreparISBN(String isbn);

    public List<Livre> chercherLivre(String isbn);

    public void supprimerLivre(String isbn);

    public void modifierLivre(Livre livre);

}
