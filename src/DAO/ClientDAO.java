package DAO;

import Model.Client;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {
    private String QUERYCREATETABLE = "CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), password VARCHAR(100), listIdAllergenes VARCHAR(100), boursier BOOLEAN)";
    private static final String CREATE_CLIENT_QUERY = "INSERT INTO Client (nom, prenom, email, password, listIdAllergenes, boursier) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CLIENT_QUERY = "DELETE FROM Client WHERE id=?;";
    private static final String FIND_CLIENTS_QUERY = "SELECT id, nom, prenom, email, password, listIdAllergenes, boursier FROM Client;";
    private Connection connection;

    public ClientDAO(Connection connection) {
        this.connection = connection;
    }
    public long createClient(Client client) throws DaoException {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
            PreparedStatement statement = connection.prepareStatement(CREATE_CLIENT_QUERY);
            statement.setString(1, client.getNom());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getEmail());
            statement.setString(4, client.getPassword());
            String listIdAllergenes = String.join(",", client.getListIdAllergenes().stream().map(String::valueOf).toArray(String[]::new));
            statement.setString(5, listIdAllergenes);
            statement.setBoolean(6, client.getBoursier());
            statement.execute();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            connection.close();
            statement.close();
            resultSet.close();
        }
        catch (SQLException e) {
            throw new DaoException(e.getMessage());
        } return 0;
    }

    public long delete(Client client) throws DaoException {
        try {
            Connection connexion = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
            PreparedStatement statement = connexion.prepareStatement(DELETE_CLIENT_QUERY);
            statement.setInt(1, client.getId());
            statement.execute();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return client.getId();
    }

    public boolean mailexist(String mail) throws DaoException{
        boolean exists = false;
        try (Connection connection = DriverManager.getConnection("jdbc:h2:~/RentManagerDatabase", "", "");
             PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) AS count FROM Client WHERE email = ?")) {
            statement.setString(1, mail);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    exists = count > 0;
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return exists;
    }
    public List<Client> getAllClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Client";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                clients.add(extractClientFromResultSet(resultSet));
            }
        }
        return clients;
    }

    private Client extractClientFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        boolean boursier = resultSet.getBoolean("boursier");
        String[] allergenIdsArray = resultSet.getString("listIdAllergenes").split(",");
        List<Integer> listIdAllergenes = new ArrayList<>();
        for (String idString : allergenIdsArray) {
            listIdAllergenes.add(Integer.parseInt(idString));
        }
        return new Client(id, nom, prenom, email, password, listIdAllergenes, boursier);
    }

    public Client findById(int id) throws DaoException {
        String query = "SELECT * FROM Client WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractClientFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return null;
    }

    public int countClients() throws DaoException {
        String query = "SELECT COUNT(*) AS count FROM Client";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return 0;
    }

}