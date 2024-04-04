package Model;

public class Stock {

    private String id;
    private int quantite;

    private int idProduit;

    public Stock() {
    }
    public Stock(String id, int quantite, int idProduit) {
        this.id = id;
        this.quantite = quantite;
        this.idProduit = idProduit;
    }
    public Stock(int quantite, int idProduit) {
        this.quantite = quantite;
        this.idProduit = idProduit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
}
