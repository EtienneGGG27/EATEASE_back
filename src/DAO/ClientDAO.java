package DAO;

import Model.Client;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDAO {
    private String QUERYCREATETABLE = "CREATE TABLE IF NOT EXISTS Client (id INT PRIMARY KEY AUTO_INCREMENT, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), password VARCHAR(100))";
    private String INSERT_CLIENT_SQL = "INSERT INTO Client" + "  (nom, prenom, email, password) VALUES " +
            " (?, ?, ?, ?);";

    public void createTable() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "root");
             PreparedStatement preparedStatement = connection.prepareStatement(QUERYCREATETABLE)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void insertClient(Client client) throws SQLException {
        System.out.println(INSERT_CLIENT_SQL);
        // Établir une connexion
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "root", "root");
             // Créer une déclaration en utilisant une connexion
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CLIENT_SQL)) {
            preparedStatement.setString(1, client.getNom());
            preparedStatement.setString(2, client.getPrenom());
            preparedStatement.setString(3, client.getEmail());
            preparedStatement.setString(4, client.getPassword());
            System.out.println(preparedStatement);
            // Exécuter la requête
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}