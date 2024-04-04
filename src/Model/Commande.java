package Model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Commande {

    private int id;
    private double prix;
    private LocalDate date;
    private int idClient;

    private List<Integer> idProduit;

    public Commande() {
    }

    public Commande(int id, double prix, LocalDate date, int idClient, List<Integer> idProduit) {
        this.id = id;
        this.prix = prix;
        this.date = date;
        this.idClient = idClient;
        this.idProduit = idProduit;
    }

    public Commande(double prix, LocalDate date, int idClient, List<Integer> idProduit) {
        this.prix = prix;
        this.date = date;
        this.idClient = idClient;
        this.idProduit = idProduit;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(List<Integer> idProduit) {
        this.idProduit = idProduit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
}
