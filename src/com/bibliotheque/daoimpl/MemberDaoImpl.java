package com.bibliotheque.daoimpl;

import com.bibliotheque.dao.MemberDao;
import com.bibliotheque.entity.Livre;
import com.bibliotheque.entity.Membre;
import com.bibliotheque.util.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDaoImpl implements MemberDao {
    Connection connection = DbConnection.createDbConnection();

    @Override
    public Membre ajouterMember(Membre membre) {
        String query = "INSERT INTO Member VALUES (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, membre.getMemberNémuro());
            preparedStatement.setString(2, membre.getMemberNom());
            preparedStatement.setInt(3, membre.getMemberTelephon());
            preparedStatement.setString(4, membre.getMemberCin());
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                return membre;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean supprimerMember(Integer membernémuro) {
        String query = "DELETE FROM Member WHERE membernémuro = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, membernémuro);
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Membre> afficherMembers() {
        String query = "SELECT * FROM Member ";
        List<Membre> membres = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Membre membre = new Membre();
                membre.setMemberNémuro(resultSet.getInt("membernémuro"));
                membre.setMemberNom(resultSet.getString("membernom"));
                membre.setMemberTelephon(resultSet.getInt("membertelephone"));
                membre.setMemberCin(resultSet.getString("membercin"));
                membres.add(membre);
            }
            return membres;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Membre modifierMember(Membre membre) {
        String query = "UPDATE Member SET membernom = ?, membertelephone = ?, membercin = ? WHERE membernémuro = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, membre.getMemberNom());
            preparedStatement.setInt(2, membre.getMemberTelephon());
            preparedStatement.setString(3, membre.getMemberCin());
            preparedStatement.setInt(4, membre.getMemberNémuro());
            Integer result = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (result != 0)
                return membre;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Membre chercherMember(Integer memebernémuro) {
        String query = "SELECT * FROM Member WHERE membernémuro = ?";
        Membre membre = new Membre();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, memebernémuro);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                membre.setMemberNémuro(resultSet.getInt("membernémuro"));
                membre.setMemberNom(resultSet.getString("membernom"));
                membre.setMemberTelephon(resultSet.getInt("membertelephone"));
                membre.setMemberCin(resultSet.getString("membercin"));
                return membre;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
