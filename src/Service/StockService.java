package Service;

import DAO.DaoException;
import DAO.StockDAO;
import java.sql.Connection;


public class StockService {

    private StockDAO stockDAO;

    public StockService(Connection connection) {
        this.stockDAO = new StockDAO(connection);
    }

    public void augmenterStock(int idProduit, int quantityToAdd) throws ServiceException {
        try {
            stockDAO.augmenterStock(idProduit, quantityToAdd);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void diminuerStock(int idProduit, int quantityToRemove) throws ServiceException {
        try {
            stockDAO.diminuerStock(idProduit, quantityToRemove);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void setStock(int idProduit, int quantity)  throws ServiceException{
        try {
            stockDAO.setStock(idProduit, quantity);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int getStock(int idProduit) throws ServiceException {
        try {
            return stockDAO.getStock(idProduit);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void ajouterStock(int idProduit, int quantity) throws ServiceException {
        try {
            stockDAO.ajouterStock(idProduit, quantity);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void supprimerStock(int idProduit)  throws ServiceException {
        try {
            stockDAO.supprimerStock(idProduit);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void supprimerToutStock()  throws ServiceException {
        try {
            stockDAO.supprimerAllStock();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}
