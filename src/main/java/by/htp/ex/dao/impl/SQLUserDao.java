package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.UserDao;

public class SQLUserDao implements UserDao {
    @Override
    public boolean authorization(String login, String password) throws DaoException{
        //TODO: add functionality;

        return false;
    }

    @Override
    public boolean registration(NewUserInfo user) throws DaoException {
        //TODO: add functionality;
        return false;
    }
}
