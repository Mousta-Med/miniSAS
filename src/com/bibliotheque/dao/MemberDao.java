package com.bibliotheque.dao;

import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Membre;

import java.util.List;

public interface MemberDao {
    public Membre ajouterMember(Membre membre);

    public Boolean supprimerMember(Integer membrenémuro);

    public List<Membre> afficherMembers();

    public Membre modifierMember(Membre membre);

    public Membre chercherMember(Integer membrenémuro);
}
