package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.EmpruntDao;
import com.bibliotheque.entity.Emprunt;
import com.bibliotheque.util.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    public Boolean supprimerEmprunt() {
        return null;
    }
}
