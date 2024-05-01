package Service;

import DAO.DaoException;

public class ServiceException extends Exception {
    public ServiceException(String message, DaoException e) {
        super(message,e);
    }
}
