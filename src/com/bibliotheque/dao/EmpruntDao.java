package com.bibliotheque.dao;

import com.bibliotheque.entity.Emprunt;

public interface EmpruntDao {
    public Emprunt ajouterEmprunt(Emprunt emprunt);

    public Emprunt chercheEmprunt(String isbn);

    public Boolean supprimerEmprunt(String isbn);
}
