package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivreDaoImpl implements LivreDao {
    Connection connection = DbConnection.createDbConnection();

    @Override
    public void ajouterLivre(Livre livre) {
        String query = "INSERT INTO Livre VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, livre.getISBN());
            preparedStatement.setString(2, livre.getTitre());
            preparedStatement.setString(3, livre.getAuteur());
            preparedStatement.setObject(4, livre.getStatut(), Types.OTHER);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                System.out.println("Livre A été Bien Ajouter");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                // 23505 is the SQLState for a unique constraint violation
                System.out.println("ISBN déjà utilisé");
            } else {
                e.printStackTrace(); // Handle other SQL exceptions
            }
        }
    }

    @Override
    public void afficherLivre() {
        String query = "SELECT * FROM Livre WHERE statut = 'DISPONIBLE'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("------------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |\n", "ISBN", "Titre", "Auteur");
            System.out.println("------------------------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            }
            System.out.println("------------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Livre afficherLivreISBN(String isbn) {
        String query = "SELECT * FROM Livre WHERE isbn = ?";
        Livre livre = new Livre();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("-------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-20s |\n", "ISBN", "Titre", "Auteur");
            System.out.println("-------------------------------------------------------");
            if (resultSet.next()) {
                System.out.printf("| %-10s | %-20s | %-20s |\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
            } else {
                System.out.println("il n'y a pas de livre avec cette isbn");
            }
            System.out.println("-------------------------------------------------------");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Livre afficherLivreparISBN(String isbn) {
        String query = "SELECT * FROM Livre WHERE isbn = ?";
        Livre livre = new Livre();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                livre.setISBN(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                return livre;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Livre> chercherLivre(String titre) {
        String query = "SELECT * FROM Livre WHERE titre LIKE ? OR auteur LIKE ?;";
        List<Livre> livres = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + titre + "%");
            preparedStatement.setString(2, "%" + titre + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Livre livre = new Livre();
                livre.setISBN(resultSet.getString("isbn"));
                livre.setTitre(resultSet.getString("titre"));
                livre.setAuteur(resultSet.getString("auteur"));
                livres.add(livre);
            }
            return livres;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public void supprimerLivre(String isbn) {
        String query = "DELETE FROM Livre WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                System.out.println("Livre A été Bien Supprimer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierLivre(Livre livre) {
        String query = "UPDATE Livre SET titre = ?, auteur = ?, statut = ? WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setObject(3, livre.getStatut(), Types.OTHER);
            preparedStatement.setString(4, livre.getISBN());
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                System.out.println("Livre A été Bien Modifier");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
