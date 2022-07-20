package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.NewsDao;
import by.htp.ex.service.AdminService;
import by.htp.ex.service.ServiceException;

public class AdminServiceImpl implements AdminService {
    NewsDao newsDao = DaoProvider.getInstance().getNewsDao();


    @Override
    public void block(NewUserInfo user) throws ServiceException {
        //TODO add functionality to block user
    }

    @Override
    public boolean edit(String text) throws ServiceException {
        try {
            return newsDao.edit(text);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean add(String text) throws ServiceException {
        try {
            return newsDao.add(text);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(String text) throws ServiceException {
        try {
            return newsDao.delete(text);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
