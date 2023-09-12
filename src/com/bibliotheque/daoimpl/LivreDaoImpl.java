package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.LivreDao;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.util.DbConnection;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

public class LivreDaoImpl implements LivreDao {
    Connection connection = DbConnection.createDbConnection();


    @Override
    public Livre ajouterLivre(Livre livre) {
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
                return livre;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Livre> afficherLivre() {
        String query = "SELECT * FROM Livre WHERE statut = 'DISPONIBLE'";
        List<Livre> livres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
    public List<Livre> afficherLivreEmprunter() {
        String query = "SELECT * FROM Livre WHERE statut = 'EMPRUNTER'";
        List<Livre> livres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
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
                livre.setStatut(Livre.Statut.valueOf(resultSet.getString("statut")));
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
    public Boolean supprimerLivre(String isbn) {
        String query = "DELETE FROM Livre WHERE isbn = ?";
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

    @Override
    public Livre modifierLivre(Livre livre) {
        String query = "UPDATE Livre SET titre = ?, auteur = ? WHERE isbn = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, livre.getTitre());
            preparedStatement.setString(2, livre.getAuteur());
            preparedStatement.setString(3, livre.getISBN());
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                return livre;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updatePérduLivre() {
        String query = "SELECT update_perdu_livre()";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeQuery();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResultSet livreStatistique() {
        String query = "SELECT livre.statut , count(*) from livre group by statut;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void createFile(ResultSet stats) {
        try {
            File file = new File("C:\\Users\\adm\\Onedrive\\Desktop\\report.txt");
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            if (stats != null) {
                writer.write("Bibliotheque Rapport:\n");
                while (stats.next()) {
                    writer.write(stats.getString("statut") + ":" + stats.getInt("count") + "\n");
                }
                writer.close();
                System.out.println("Fichier bien créé");
            } else {
                System.out.println("ResultSet is null");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


}
