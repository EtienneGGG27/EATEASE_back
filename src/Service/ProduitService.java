package Service;

import DAO.ProduitDAO;
import Model.Produit;
import DAO.DaoException;
import java.sql.Connection;
import java.util.List;

public class ProduitService {
    private ProduitDAO produitDAO;

    public ProduitService(Connection connection) {
        this.produitDAO = new ProduitDAO(connection);
    }

    public List<Produit> getAllProduits() throws ServiceException {
        try {
            return produitDAO.getAllProduits();
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération de tous les produits", e);
        }
    }

    public void ajouterProduit(Produit produit) throws ServiceException {
        try {
            produitDAO.ajouterProduit(produit);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de l'ajout du produit", e);
        }
    }

    public void modifierProduit(Produit produit) throws ServiceException {
        try {
            produitDAO.modifierProduit(produit);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la modification du produit", e);
        }
    }

    public void supprimerProduit(int id) throws ServiceException {
        try {
            produitDAO.supprimerProduit(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la suppression du produit", e);
        }
    }

    public Produit getProduitById(int id) throws ServiceException {
        try {
            return produitDAO.getProduitById(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération du produit par ID", e);
        }
    }

    public List<Produit> getProduitsByType(String type) throws ServiceException {
        try {
            return produitDAO.getProduitsByType(type);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération des produits par type", e);
        }
    }

    public List<Produit> getProduitsByMenuId(int id) throws ServiceException {
        try {
            return produitDAO.getProduitsByMenuId(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération des produits par ID de menu", e);
        }
    }

    public List<Produit> getProduitsByAllergeneId(int id) throws ServiceException {
        try {
            return produitDAO.getProduitsByAllergeneId(id);
        } catch (DaoException e) {
            throw new ServiceException("Erreur lors de la récupération des produits par ID d'allergène", e);
        }
    }
}
