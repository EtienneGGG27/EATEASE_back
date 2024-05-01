package DAO;

import Model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    private static final String INSERT_ADMIN_QUERY = "INSERT INTO Admin (nom, prenom, email, password) VALUES (?, ?, ?, ?)";
    private static final String FIND_ADMIN_BY_EMAIL_QUERY = "SELECT * FROM Admin WHERE email = ?";
    private static final String DELETE_ADMIN_QUERY = "DELETE FROM Admin WHERE id=?";
    private static final String COUNT_ADMINS_QUERY = "SELECT COUNT(*) AS count FROM Admin";
    private static final String FIND_ADMIN_BY_ID_QUERY = "SELECT * FROM Admin WHERE id = ?";
    private Connection connection;

    public AdminDAO(Connection connection) {
        this.connection = connection;
    }

    public void createAdmin(Admin admin) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_ADMIN_QUERY);
            statement.setString(1, admin.getNom());
            statement.setString(2, admin.getPrenom());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
    public void deleteAdmin(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_ADMIN_QUERY);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    public int countAdmins() throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(COUNT_ADMINS_QUERY);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count");
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return 0;
    }

    public Admin findAdminById(int id) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ADMIN_BY_ID_QUERY);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return extractAdminFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
        return null;
    }



    private Admin extractAdminFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nom = resultSet.getString("nom");
        String prenom = resultSet.getString("prenom");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        return new Admin(id, nom, prenom, email, password);
    }
}