package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.IUserService;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
    private final UserDataValidation userDataValidation = ValidationProvider.getInstance().getUserDataValidation();

    @Override
    public String signIn(String login, String password) throws ServiceException {
        try {
            if (userDAO.logination(login, password)) {
                return userDAO.getRole(login, password);
            } else {
                return UsersRole.GUEST;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean registration(NewUserInfo user) throws ServiceException {
        boolean result = false;
        try {
            if (userDataValidation.checkRegData(user)) {
                result = userDAO.registration(user);
                return result;
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return result;
    }
}