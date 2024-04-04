package Model;

import java.time.LocalDate;

public class Approvisionner {

    private int id;
    private int idProduit;
    private int quantiteACommander;
    private LocalDate date;

    public Approvisionner() {
    }
    public Approvisionner(int id, int idProduit, int quantiteACommander, LocalDate date) {
        this.id = id;
        this.idProduit = idProduit;
        this.quantiteACommander = quantiteACommander;
        this.date = date;
    }
    public Approvisionner(int idProduit, int quantiteACommander, LocalDate date) {
        this.idProduit = idProduit;
        this.quantiteACommander = quantiteACommander;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantiteACommander() {
        return quantiteACommander;
    }

    public void setQuantiteACommander(int quantiteACommander) {
        this.quantiteACommander = quantiteACommander;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
