package Service;

import DAO.ClientDAO;
import DAO.DaoException;
import Model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
    }

    public long create(Client client) throws ServiceException {
        try {
            // Add validation and business logic
            return clientDAO.createClient(client);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Client findById(long id) throws ServiceException {
        try {
            return clientDAO.findById((int)id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Client> findAll() throws ServiceException {
        try {
            return clientDAO.getAllClients();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long delete(Client client) throws ServiceException {
        try {
            return clientDAO.delete(client);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int count() throws ServiceException {
        try {
            return clientDAO.countClients();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}