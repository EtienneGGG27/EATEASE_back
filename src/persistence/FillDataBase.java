package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.tools.DeleteDbFiles;


public class FillDataBase {


    public static void main(String[] args) throws Exception {
        try {
            DeleteDbFiles.execute("~", "EateaseDatabase", true);
            insertWithPreparedStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertWithPreparedStatement() throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement createPreparedStatement = null;

        List<String> createTablesQueries = new ArrayList<>();
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Client(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), password VARCHAR(100), listIdAllergenes VARCHAR(100), boursier BOOLEAN)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Admin(id INT primary key auto_increment, nom VARCHAR(100), prenom VARCHAR(100), email VARCHAR(100), password VARCHAR(100))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Produit(id INT primary key auto_increment, nom VARCHAR(100), prix FLOAT, description VARCHAR(100), listIdAllergenes VARCHAR(100), typeProduit VARCHAR(100), listIdMenu VARCHAR(100))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Menu(id INT primary key auto_increment, nom VARCHAR(100), prix FLOAT, description VARCHAR(100), idProduit VARCHAR(100))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Commande(id INT primary key auto_increment, date DATE, idClient INT, listIdProduits VARCHAR(100), prix FLOAT)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Allergene(id INT primary key auto_increment, nom VARCHAR(100), description VARCHAR(100))");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Approvisionner(id INT primary key auto_increment, idProduit INT, foreign key (idProduit) REFERENCES Produit(id), quantiteACommander INT, date DATE)");
        createTablesQueries.add("CREATE TABLE IF NOT EXISTS Stock(id INT primary key auto_increment, idProduit INT, quantite INT, foreign key (idProduit) REFERENCES Produit(id))");
        try {
            connection.setAutoCommit(false);

            for (String createQuery : createTablesQueries) {
                createPreparedStatement = connection.prepareStatement(createQuery);
                createPreparedStatement.executeUpdate();
                createPreparedStatement.close();
            }

            // Remplissage de la base de donnée
            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Client(nom, prenom, email, password, listIdAllergenes, boursier) VALUES ('Doe', 'John', 'john.doe@email.com', 'password', '1,2', true)");
            stmt.execute("INSERT INTO Client(nom, prenom, email, password, listIdAllergenes, boursier) VALUES ('Doe', 'Jane','jane.doe@email.com', 'password', '1,2', false)");
            stmt.execute("INSERT INTO Admin(nom, prenom, email, password) VALUES ('Madame', 'CROUS', 'crous@email.com', 'password')");
            stmt.execute("INSERT INTO Produit(nom, prix, description, listIdAllergenes, typeProduit, listIdMenu) VALUES ('Pain', 1.5, 'Pain au levain', '1,2', 'entree', '1')");
            stmt.execute("INSERT INTO Produit(nom, prix, description, listIdAllergenes, typeProduit, listIdMenu) VALUES ('Poulet', 5.5, 'Poulet fermier', '1,2', 'plat', '2')");
            stmt.execute("INSERT INTO Produit(nom, prix, description, listIdAllergenes, typeProduit, listIdMenu) VALUES ('Tarte', 3.5, 'Tarte aux pommes', '1,2', 'dessert', '3')");
            stmt.execute("INSERT INTO Menu(nom, prix, description, idProduit) VALUES ('Menu 1', 10.5, 'Menu complet', '1,2,3')");
            stmt.execute("INSERT INTO Commande(date, idClient, listIdProduits, prix) VALUES ('2021-01-01', 1, '1,2,3', 10.5)");
            stmt.execute("INSERT INTO Allergene(nom, description) VALUES ('Gluten', 'Allergene au gluten')");
            stmt.execute("INSERT INTO Allergene(nom, description) VALUES ('Lactose', 'Allergene au lactose')");
            stmt.execute("INSERT INTO Approvisionner(idProduit, quantiteACommander, date) VALUES (1, 100, '2021-01-01')");
            stmt.execute("INSERT INTO Stock(idProduit, quantite) VALUES (1, 100)");


            connection.commit();
            System.out.println("Success!");
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getLocalizedMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
