package Service;

import DAO.AdminDAO;
import DAO.DaoException;
import Model.Admin;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO adminDAO) {
        this.adminDAO = adminDAO;
    }

    public void createAdmin(Admin admin) throws ServiceException {
        try {
            adminDAO.createAdmin(admin);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public void deleteAdmin(int id) throws ServiceException {
        try {
            adminDAO.deleteAdmin(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public int countAdmins() throws ServiceException {
        try {
            return adminDAO.countAdmins();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Admin findAdminById(int id) throws ServiceException {
        try {
            return adminDAO.findAdminById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}