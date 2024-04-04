package Model;

import java.util.List;

public class Client {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private int id;
    private Boolean boursier;

    private List<Integer> listIdAllergenes;

    public Client() {
    }

    public Client(int id, String nom, String prenom, String email, String password, List<Integer> listIdAllergenes, Boolean boursier) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.id = id;
        this.listIdAllergenes = listIdAllergenes;
        this.boursier = boursier;
    }

    public Client(String nom, String prenom, String email, String password, List<Integer> listIdAllergenes, Boolean boursier) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.listIdAllergenes = listIdAllergenes;
        this.boursier = boursier;
    }

    public Boolean getBoursier() {
        return boursier;
    }

    public void setBoursier(Boolean boursier) {
        this.boursier = boursier;
    }

    public String getNom() {
        return nom;
    }

    public List<Integer> getListIdAllergenes() {
        return listIdAllergenes;
    }

    public void setListIdAllergenes(List<Integer> listIdAllergenes) {
        this.listIdAllergenes = listIdAllergenes;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", id=" + id + '\'' +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password +
                '}';
    }
}
