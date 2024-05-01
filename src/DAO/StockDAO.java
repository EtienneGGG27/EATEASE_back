package DAO;

import persistence.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StockDAO {

    private Connection connection;
    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    public void augmenterStock(int idProduit, int quantityToAdd) throws DaoException {
        String query = "UPDATE Stock SET quantite = quantite + ? WHERE idProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quantityToAdd);
            statement.setInt(2, idProduit);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void diminuerStock(int idProduit, int quantityToRemove) throws DaoException {
        String query = "UPDATE Stock SET quantite = quantite - ? WHERE idProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quantityToRemove);
            statement.setInt(2, idProduit);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void setStock(int idProduit, int quantity) throws DaoException {
        String query = "UPDATE Stock SET quantite = ? WHERE idProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, quantity);
            statement.setInt(2, idProduit);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public int getStock(int idProduit) throws DaoException {
        String query = "SELECT quantite FROM Stock WHERE idProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProduit);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("quantite");
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return 0;
    }

    public void ajouterStock(int idProduit, int quantity) throws DaoException {
        String query = "INSERT INTO Stock(idProduit, quantite) VALUES(?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProduit);
            statement.setInt(2, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void supprimerStock(int idProduit) throws DaoException {
        String query = "DELETE FROM Stock WHERE idProduit = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idProduit);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public void supprimerAllStock() throws DaoException {
        String query = "DELETE FROM Stock";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }


//  public static void main(String[] args) {
//        try {
//      try {
//          Connection connection = ConnectionManager.getConnection();
//      } catch (SQLException e) {
//          throw new RuntimeException(e);
//      }
//            StockDAO stockDAO = new StockDAO(connection);
//            stockDAO.addStock(1, 100);
//            System.out.println(stockDAO.getStock(1));
//            stockDAO.increaseStock(1, 50);
//            System.out.println(stockDAO.getStock(1));
//            stockDAO.decreaseStock(1, 25);
//            System.out.println(stockDAO.getStock(1));
//            stockDAO.removeStock(1);
//            System.out.println(stockDAO.getStock(1));
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//  }


}
