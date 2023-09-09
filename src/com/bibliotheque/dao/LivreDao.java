package com.bibliotheque.dao;

import com.bibliotheque.entity.Livre;

import java.util.List;

public interface LivreDao {
    public Livre ajouterLivre(Livre livre);

    public List<Livre> afficherLivre();

    public List<Livre> afficherLivreEmprunter();

    public Livre afficherLivreparISBN(String isbn);

    public List<Livre> chercherLivre(String isbn);

    public Boolean supprimerLivre(String isbn);

    public Livre modifierLivre(Livre livre);

}
