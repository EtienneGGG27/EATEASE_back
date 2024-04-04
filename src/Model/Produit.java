package Model;

import java.util.List;

public class Produit {

    private int id;
    private String nom;
    private String description;
    private double prix;
    private List<Integer> idAllergenes;
    private String typeProduit;
    private List<Integer> idMenu;

    public Produit() {
    }

    public Produit(int id, String nom, String description, double prix, List<Integer> idAllergenes, String typeProduit, List<Integer> idMenu) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.idAllergenes = idAllergenes;
        this.typeProduit = typeProduit;
        this.idMenu = idMenu;
    }

    public Produit(String nom, String description, double prix, List<Integer> idAllergenes, String typeProduit, List<Integer> idMenu) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.idAllergenes = idAllergenes;
        this.typeProduit = typeProduit;
        this.idMenu = idMenu;
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

    public List<Integer> getIdAllergenes() {
        return idAllergenes;
    }

    public void setIdAllergenes(List<Integer> idAllergenes) {
        this.idAllergenes = idAllergenes;
    }

    public String getTypeProduit() {
        return typeProduit;
    }

    public void setTypeProduit(String typeProduit) {
        this.typeProduit = typeProduit;
    }

    public List<Integer> getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(List<Integer> idMenu) {
        this.idMenu = idMenu;
    }
}
