package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceNewsException;

public class NewsServiceImpl implements INewsService {

    private final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

    @Override
    public void find() {
    }

    @Override
    public void update() {
    }

    @Override
    public List<News> latestList(int count) throws ServiceNewsException {
        try {
            return newsDAO.getLatestsList(count);
        } catch (NewsDAOException e) {
            throw new ServiceNewsException(e);
        }
    }

    @Override
    public List<News> list() throws ServiceNewsException {
        try {
            return newsDAO.getList();
        } catch (NewsDAOException e) {
            throw new ServiceNewsException(e);
        }
    }

    @Override
    public News findById(int id) throws ServiceNewsException {
        try {
            return newsDAO.fetchById(id);
        } catch (NewsDAOException e) {
            throw new ServiceNewsException(e);
        }
    }

    @Override
    public boolean save(News news) throws ServiceNewsException {
        try {
            if (newsDAO.addNews(news) == 0) {
                return false;
            }
            return true;
        } catch (NewsDAOException e) {
            throw new ServiceNewsException(e);
        }
    }
}