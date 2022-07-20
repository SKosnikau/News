package by.htp.ex.dao;

public interface NewsDao {
    boolean add(String text) throws DaoException;
    boolean edit(String text) throws DaoException;
    boolean delete(String text) throws DaoException;
}
