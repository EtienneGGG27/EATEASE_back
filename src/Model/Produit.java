package Model;

import java.util.List;

public class Produit {

    private int id;
    private String nom;
    private String description;
    private double prix;
    private List<Integer> listIdAllergenes;
    private String typeProduit;
    private List<Integer> listIdMenu;

    public Produit() {
    }

    public Produit(int id, String nom, String description, double prix, List<Integer> listIdAllergenes, String typeProduit, List<Integer> idMenu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.listIdAllergenes = listIdAllergenes;
        this.typeProduit = typeProduit;
        this.listIdMenu = idMenu;
    }

    public Produit(String nom, String description, double prix, List<Integer> listIdAllergenes, String typeProduit, List<Integer> idMenu) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.listIdAllergenes = listIdAllergenes;
        this.typeProduit = typeProduit;
        this.listIdMenu = idMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public List<Integer> getListIdAllergenes() {
        return listIdAllergenes;
    }

    public void setListIdAllergenes(List<Integer> listIdAllergenes) {
        this.listIdAllergenes = listIdAllergenes;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public List<Integer> getListIdMenu() {
        return listIdMenu;
    }

    public void setListIdMenu(List<Integer> listIdMenu) {
        this.listIdMenu = listIdMenu;
    }
}
