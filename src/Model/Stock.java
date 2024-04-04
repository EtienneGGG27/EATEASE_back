package Model;

public class Stock {

    private String id;
    private int quanite;

    private int idProduit;

    public Stock() {
    }
    public Stock(String id, int quanite, int idProduit) {
        this.id = id;
        this.quanite = quanite;
        this.idProduit = idProduit;
    }
    public Stock(int quanite, int idProduit) {
        this.quanite = quanite;
        this.idProduit = idProduit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuanite() {
        return quanite;
    }

    public void setQuanite(int quanite) {
        this.quanite = quanite;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
}
