package DAO;

import Model.Commande;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private String QUERY_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Commande(id INT primary key auto_increment, date DATE, idClient INT, listIdProduits VARCHAR(100), prix FLOAT)";
    private static final String INSERT_COMMANDE_QUERY = "INSERT INTO Commande (date, idClient, listIdProduits, prix) VALUES (?, ?, ?, ?)";
    private static final String DELETE_COMMANDE_QUERY = "DELETE FROM Commande WHERE id=?";
    private static final String FIND_COMMANDE_QUERY = "SELECT id, date, idClient, listIdProduits, prix FROM Commande WHERE id=?";
    private static final String COUNT_COMMANDES_QUERY = "SELECT COUNT(*) AS count FROM Commande";
    private Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public void createCommande(Commande commande) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_COMMANDE_QUERY);
            statement.setDate(1, Date.valueOf(commande.getDate()));
            statement.setInt(2, commande.getIdClient());
            String listIdProduits = String.join(",", commande.getListIdProduits().stream().map(String::valueOf).toArray(String[]::new));
            statement.setString(3, listIdProduits);
            statement.setDouble(4, commande.getPrix());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Commande findCommandeById(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_COMMANDE_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractCommandeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    public int countCommandes() throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(COUNT_COMMANDES_QUERY);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return 0;
    }

    public void deleteCommande(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_COMMANDE_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    private Commande extractCommandeFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        LocalDate date = resultSet.getDate("date").toLocalDate();
        int idClient = resultSet.getInt("idClient");
        double prix = resultSet.getDouble("prix");
        String[] produitsArray = resultSet.getString("listIdProduits").split(",");
        List<Integer> listIdProduits = new ArrayList<>();
        for (String idString : produitsArray) {
            listIdProduits.add(Integer.parseInt(idString));
        }
        return new Commande(id, prix, date, idClient, listIdProduits);
    }
}