package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.util.DbConnection;

import java.sql.*;


public class LivreDaoImpl implements LivreDao {
    Connection connection;
    @Override
    public void ajouterLivre(Livre livre) {
        connection = DbConnection.createDbConnection();
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
        connection = DbConnection.createDbConnection();
        String query = "SELECT * FROM Livre WHERE statut = 'DISPONIBLE'";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            System.out.println("-------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur");
            System.out.println("-------------------------------------------------------");
            while (resultSet.next()) {
                System.out.printf("| %-10s | %-20s | %-15s |\n",
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3));}
            System.out.println("-------------------------------------------------------");

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void afficherLivreISBN(String isbn) {
        connection = DbConnection.createDbConnection();
        String query = "SELECT * FROM Livre WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, isbn);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("-------------------------------------------------------");
            System.out.printf("| %-10s | %-20s | %-15s |\n", "ISBN", "Titre", "Auteur");
            System.out.println("-------------------------------------------------------");
            if(!resultSet.next()){
                System.out.println("il n'y a pas de livre ajouté");
            }else {
                while (resultSet.next()) {
                    System.out.printf("| %-10s | %-20s | %-15s |\n",
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3));}
            }
            System.out.println("-------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerLivre(String isbn) {
        connection = DbConnection.createDbConnection();
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
    public void modifierLivre() {

    }
}
