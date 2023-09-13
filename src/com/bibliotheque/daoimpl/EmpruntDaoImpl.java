package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.EmpruntDao;
import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.dao.MemberDao;
import com.bibliotheque.entity.Emprunt;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Membre;
import com.bibliotheque.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpruntDaoImpl implements EmpruntDao {
    Connection connection = DbConnection.createDbConnection();
    @Override
    public Emprunt ajouterEmprunt(Emprunt emprunt) {
        String query = "INSERT INTO Emprunt VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, emprunt.getLivre().getISBN());
            preparedStatement.setInt(2, emprunt.getMembre().getMemberNémuro());
            preparedStatement.setDate(3, emprunt.getDate_demprunt());
            preparedStatement.setDate(4, emprunt.getDate_fin_demprunt());
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                return emprunt;
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return null;
    }

    @Override
    public Emprunt chercheEmprunt(String isbn) {
        String query = "SELECT * FROM Emprunt WHERE livreisbn = ?";
        Emprunt emprunt = new Emprunt();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            LivreDao livreDao = new LivreDaoImpl();
            MemberDao memberDao = new MemberDaoImpl();
            if (resultSet.next()) {
                Livre livre = livreDao.afficherLivreparISBN(resultSet.getString("livreisbn"));
                Membre membre = memberDao.chercherMember(resultSet.getInt("membernémuro"));
                emprunt.setMembre(membre);
                emprunt.setLivre(livre);
                emprunt.setDate_demprunt(resultSet.getDate("datedemprunt"));
                emprunt.setDate_fin_demprunt(resultSet.getDate("datefindemprunt"));
                return emprunt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean supprimerEmprunt(String isbn) {
        String query = "DELETE FROM Emprunt WHERE livreisbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
