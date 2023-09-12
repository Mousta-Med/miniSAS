package com.bibliotheque.dao;

import com.bibliotheque.entity.Livre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface LivreDao {

    public Livre ajouterLivre(Livre livre);

    public List<Livre> afficherLivre();

    public List<Livre> afficherLivreEmprunter();

    public Livre afficherLivreparISBN(String isbn);

    public List<Livre> chercherLivre(String isbn);

    public Boolean supprimerLivre(String isbn);

    public Livre modifierLivre(Livre livre);

    public void updatePérduLivre();

    public ResultSet livreStatistique();

    public void createFile(ResultSet resultSet);

}
