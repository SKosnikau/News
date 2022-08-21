package by.htp.ex.dao.impl;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.NewsDAOException;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public class NewsDAO implements INewsDAO {
    private static final String SHOW_LATEST_NEWS_FOR_GUEST = "SELECT * FROM news ORDER BY data DESC LIMIT ?";
    private static final String SHOW_NEWS_ON_1ST_PAGE = "SELECT * FROM news ORDER BY data DESC";
    private static final String VIEW_NEWS_BY_ID = "SELECT * FROM news WHERE id_news=?";
    private static final String ADD_NEWS = "INSERT INTO news(title, brief, content, date) VALUE(?,?,?)";
    private static final String UPDATE_NEWS = "UPDATE news SET title = ? , brief = ?, content = ? WHERE id = ?";
    private static final String DELETE_NEWS = "DELETE WHERE id = ?";

    @Override
    public List<News> getLatestsList(int count) throws NewsDAOException {
        List<News> result = new ArrayList<News>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection(); PreparedStatement ps = connection.prepareStatement(SHOW_LATEST_NEWS_FOR_GUEST)) {
            ps.setInt(1, count);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Timestamp timestamp = rs.getTimestamp(5);
                String data = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);
                News newsInLatestList = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), data);
                result.add(newsInLatestList);
            }
            return result;
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }


    @Override
    public List<News> getList() throws NewsDAOException {
        List<News> result = new ArrayList<News>();
        try (Connection connection = ConnectionPool.getInstance().takeConnection(); PreparedStatement ps = connection.prepareStatement(SHOW_NEWS_ON_1ST_PAGE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Timestamp timestamp = rs.getTimestamp(5);
                String data = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);

                News news = new News(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), data);

                result.add(news);
            }
            return result;
        } catch (SQLException e) {
            throw new NewsDAOException(e);

        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }


    @Override
    public News fetchById(int id) throws NewsDAOException {

        try (Connection connection = ConnectionPool.getInstance().takeConnection(); PreparedStatement ps = connection.prepareStatement(VIEW_NEWS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp timestamp = rs.getTimestamp(5);
            String data = new SimpleDateFormat("yyyy-MM-dd").format(timestamp);

            return new News(id, rs.getString(2), rs.getString(3), rs.getString(4), data);

        } catch (SQLException e) {
            throw new NewsDAOException(e);

        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public int addNews(News news) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection(); PreparedStatement ps = connection.prepareStatement(ADD_NEWS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getBriefNews());
            ps.setString(3, news.getContent());
            ps.setString(4, getDate());
            int rs = ps.executeUpdate();
            if (rs == 0) {
                throw new NewsDAOException("Data not saved");
            }
            ResultSet generateKey = ps.getGeneratedKeys();
            if (generateKey.next()) {
                throw new NewsDAOException("Data not saved");
            }
            System.out.println("generateKey.getInt(DatabaseTableColumn.TABLE_NEWS_COLUMN_ID_NEWS)" + generateKey.getInt(DatabaseTableColumn.TABLE_NEWS_COLUMN_ID_NEWS));
            return generateKey.getInt(DatabaseTableColumn.TABLE_NEWS_COLUMN_ID_NEWS);

        } catch (SQLException e) {
            throw new NewsDAOException(e);

        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public boolean updateNews(News news) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_NEWS)) {
            ps.setString(1, news.getTitle());
            ps.setString(2, news.getBriefNews());
            ps.setString(3, news.getContent());
            ps.setInt(4, news.getIdNews());
            int rs = ps.executeUpdate();
            if (rs == 0) {
                return false;
            }
            return true;
        } catch (SQLException e) {
            throw new NewsDAOException(e);
        } catch (ConnectionPoolException e) {
            throw new NewsDAOException(e);
        }
    }

    @Override
    public boolean deleteNewses(String[] idNewses) throws NewsDAOException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection()) {
            connection.setAutoCommit(false);
            if (deleteAllNews(connection, idNewses)) {
                return true;
            }
        } catch (ConnectionPoolException | SQLException e) {
            throw new NewsDAOException(e);
        }
        return false;
    }

    public boolean deleteAllNews(Connection connection, String[] idNewses) throws SQLException {
        try {
            connection.setAutoCommit(false);
            PreparedStatement ps = connection.prepareStatement(DELETE_NEWS);

            for (int i = 0; i < idNewses.length; i++) {
                ps.setInt(4, Integer.parseInt(idNewses[i]));
                int rs = ps.executeUpdate();
                if (rs == 0) {
                    return false;
                }
            }
            connection.commit();
            connection.setAutoCommit(true);

        } catch (SQLException e) {
            connection.rollback();
        }
        return true;
    }

    private String getDate() {
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("GMT+3"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = dateTimeFormatter.format(zonedDateTime);
        return date;
    }
}