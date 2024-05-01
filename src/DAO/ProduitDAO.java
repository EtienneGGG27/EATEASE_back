package DAO;

import Model.Produit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProduitDAO {
    private Connection connection;
    public ProduitDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Produit> getAllProduits() throws DaoException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM Produit";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String description = resultSet.getString("description");
                double prix = resultSet.getDouble("prix");
                String typeProduit = resultSet.getString("typeProduit");
                List<Integer> listIdAllergenes = getListIdFromResultSet(resultSet, "listIdAllergenes");
                List<Integer> listIdMenu = getListIdFromResultSet(resultSet, "listIdMenu");

                Produit produit = new Produit(id, nom, description, prix, listIdAllergenes, typeProduit, listIdMenu);
                produits.add(produit);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return produits;
    }

    private List<Integer> getListIdFromResultSet(ResultSet resultSet, String columnName) throws DaoException {
        try {
            String listIdString = null;
            listIdString = resultSet.getString(columnName);
        if (listIdString != null && !listIdString.isEmpty()) {
            List<Integer> listId = new ArrayList<>();
            String[] idStrings = listIdString.split(",");
            for (String idString : idStrings) {
                listId.add(Integer.parseInt(idString));
            }
            return listId;
        } else {
            return Collections.emptyList();
        }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void ajouterProduit(Produit produit) throws DaoException {
        String query = "INSERT INTO Produit(nom, description, prix, listIdAllergenes, typeProduit, listIdMenu) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produit.getNom());
            statement.setString(2, produit.getDescription());
            statement.setDouble(3, produit.getPrix());
            statement.setString(4, produit.getListIdAllergenes().toString());
            statement.setString(5, produit.getTypeProduit());
            statement.setString(6, produit.getListIdMenu().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void modifierProduit(Produit produit) throws DaoException {
        String query = "UPDATE Produit SET nom = ?, description = ?, prix = ?, listIdAllergenes = ?, typeProduit = ?, listIdMenu = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, produit.getNom());
            statement.setString(2, produit.getDescription());
            statement.setDouble(3, produit.getPrix());
            statement.setString(4, produit.getListIdAllergenes().toString());
            statement.setString(5, produit.getTypeProduit());
            statement.setString(6, produit.getListIdMenu().toString());
            statement.setInt(7, produit.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void supprimerProduit(int id) throws DaoException {
        String query = "DELETE FROM Produit WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public Produit getProduitById(int id) throws DaoException {
        String query = "SELECT * FROM Produit WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    double prix = resultSet.getDouble("prix");
                    String typeProduit = resultSet.getString("typeProduit");
                    List<Integer> listIdAllergenes = getListIdFromResultSet(resultSet, "listIdAllergenes");
                    List<Integer> listIdMenu = getListIdFromResultSet(resultSet, "listIdMenu");

                    return new Produit(id, nom, description, prix, listIdAllergenes, typeProduit, listIdMenu);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    public List<Produit> getProduitsByType(String type) throws DaoException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM Produit WHERE typeProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    double prix = resultSet.getDouble("prix");
                    List<Integer> listIdAllergenes = getListIdFromResultSet(resultSet, "listIdAllergenes");
                    List<Integer> listIdMenu = getListIdFromResultSet(resultSet, "listIdMenu");

                    Produit produit = new Produit(id, nom, description, prix, listIdAllergenes, type, listIdMenu);
                    produits.add(produit);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return produits;
    }

    public List<Produit> getProduitsByMenuId(int id) throws DaoException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM Produit WHERE listIdMenu LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + id + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int produitId = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    double prix = resultSet.getDouble("prix");
                    String typeProduit = resultSet.getString("typeProduit");
                    List<Integer> listIdAllergenes = getListIdFromResultSet(resultSet, "listIdAllergenes");
                    List<Integer> listIdMenu = getListIdFromResultSet(resultSet, "listIdMenu");

                    Produit produit = new Produit(produitId, nom, description, prix, listIdAllergenes, typeProduit, listIdMenu);
                    produits.add(produit);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return produits;
    }

    public List<Produit> getProduitsByAllergeneId(int id) throws DaoException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM Produit WHERE listIdAllergenes LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + id + "%");
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int produitId = resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String description = resultSet.getString("description");
                    double prix = resultSet.getDouble("prix");
                    String typeProduit = resultSet.getString("typeProduit");
                    List<Integer> listIdAllergenes = getListIdFromResultSet(resultSet, "listIdAllergenes");
                    List<Integer> listIdMenu = getListIdFromResultSet(resultSet, "listIdMenu");

                    Produit produit = new Produit(produitId, nom, description, prix, listIdAllergenes, typeProduit, listIdMenu);
                    produits.add(produit);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return produits;
    }
}
