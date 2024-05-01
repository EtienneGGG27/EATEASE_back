package Service;

import DAO.CommandeDAO;
import DAO.DaoException;
import Model.Commande;

public class CommandeService {
    private final CommandeDAO commandeDAO;

    public CommandeService(CommandeDAO commandeDAO) {
        this.commandeDAO = commandeDAO;
    }

    public void create(Commande commande) throws ServiceException {
        try {
            commandeDAO.createCommande(commande);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Commande findById(int id) throws ServiceException {
        try {
            return commandeDAO.findCommandeById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int count() throws ServiceException {
        try {
            return commandeDAO.countCommandes();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(int id) throws ServiceException {
        try {
            commandeDAO.deleteCommande(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}