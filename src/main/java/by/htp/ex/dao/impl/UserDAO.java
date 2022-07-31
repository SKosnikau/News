package by.htp.ex.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.ErrorsMessages;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;

public class UserDAO implements IUserDAO {

    private DataBaseStub dataBase = DataBaseStub.getInstance();
    public List<String> errorsMessagesDAO = new ArrayList<>();

    public boolean isDataBaseAvailable(String login, String password) throws DaoException {
        boolean result = false;
        try {
            if (dataBase == null) {
                getErrorsListMessage(ErrorsMessages.DATABASE_NOT_AVAILABLE);

                throw new SQLException("Database not available");
            } else {
                result = true;
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public boolean logination(String login, String password) throws DaoException {
        boolean result = false;
        if (isDataBaseAvailable(login, password)) {
            for (NewUserInfo user : dataBase.getDataBase()) {
                if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                    result = true;
                }
            }
        }
        return result;
    }

    public String getRole(String login, String password) throws DaoException {
        String role = UsersRole.GUEST;
        if (logination(login, password)) {
            for (NewUserInfo user : dataBase.getDataBase()) {
                if (user.getLogin().equals(login)) {
                    role = user.getRole();
                }
            }
        }
        return role;
    }

    @Override
    public boolean registration(NewUserInfo user) throws DaoException {
        boolean result = false;
        if (!isUserAlreadyRegistered(user)) {
            if (isLoginNotUsed(user) && isEmailNotUsed(user)) {
                addDataBase(user);
                result = true;
            }
        }
        return result;
    }

    public DataBaseStub addDataBase(NewUserInfo user) {
        dataBase.getDataBase().add(user);
        return dataBase;
    }

    public List<String> getErrorsListMessage(String message) {
        errorsMessagesDAO.add(message);
        System.out.println(errorsMessagesDAO);
        return errorsMessagesDAO;
    }

    private boolean isUserAlreadyRegistered(NewUserInfo user) {
        boolean result = true;
        for (NewUserInfo registeredUsers : dataBase.getDataBase()) {
            if (!user.equals(registeredUsers)) {
                result = false;
            } else {
                getErrorsListMessage(ErrorsMessages.USER_ALREADY_REGISTRED);
            }
        }
        return result;
    }

    public boolean isLoginNotUsed(NewUserInfo user) {
        boolean result = true;
        for (NewUserInfo userRegistered : dataBase.getDataBase()) {
            if (user.getLogin().equals(userRegistered.getLogin())) {
                result = false;
                break;
            } else {
                getErrorsListMessage(ErrorsMessages.LOGIN_ALREADY_IN_USE);
            }
        }
        return result;
    }

    public boolean isEmailNotUsed(NewUserInfo user) {
        boolean result = true;
        for (NewUserInfo userRegistred : dataBase.getDataBase()) {
            if (user.getEmail().equals(userRegistred.getEmail())) {
                result = false;
                break;
            } else {
                getErrorsListMessage(ErrorsMessages.EMAIL_ALREADY_IN_USE);
            }
        }
        return result;
    }
}