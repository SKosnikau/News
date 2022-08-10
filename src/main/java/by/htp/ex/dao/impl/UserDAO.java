package by.htp.ex.dao.impl;

import java.sql.*;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public class UserDAO implements IUserDAO {
    private static final Integer ID_UNKNOWN_ROLE = 3;
    private int idRole;
    private String role;

    //поля экземпляра класса сделать локальными
    @Override
    public boolean logination(String login, String password) throws DaoException {
        boolean result = false;

        String sql = "SELECT * FROM users WHERE login=? AND password=?";

        //над овверрайд как прайват статик файнал и дать нормальное имя

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idRole = rs.getInt(DatabaseTableColumn.TABLE_USERS_COLUMN_ID_ROLES);
                roleSearch(connection, login, password);
                result = true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public String getRole(String login, String password) {
        return UsersRole.GUEST;
    }

    private void roleSearch(Connection connection, String login, String password) throws SQLException {
        String sql = "SELECT * FROM roles WHERE id_roles=" + idRole;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);
        if (rs.next()) {
            role = rs.getString(DatabaseTableColumn.TABLE_ROLES_COLUMN_TITLE);
        }
    }

    @Override
    public boolean registration(NewUserInfo user) throws DaoException {
        boolean result = false;

        String sql = "INSERT INTO users(login,password,name,surname,email,id_roles) values (?,?,?,?,?,?)";

        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            if (!isUserAlreadyRegistered(connection, user) && isLoginNotUsed(connection, user) && isEmailNotUsed(connection, user)) {
                ps.setString(1, user.getLogin());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getUserName());
                ps.setString(4, user.getUserSurname());
                ps.setString(5, user.getEmail());
                getIdRoleByTitle(connection);
                ps.setInt(6, idRole);
                ps.executeUpdate();
                result = true;
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return result;
    }

    private void getIdRoleByTitle(Connection connection) throws SQLException {
        String sql = "SELECT * FROM roles WHERE title=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, UsersRole.USER);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idRole = rs.getInt(DatabaseTableColumn.TABLE_ROLES_COLUMN_ID_ROLES);
        } else {
            idRole = ID_UNKNOWN_ROLE;
        }
    }

    private boolean isUserAlreadyRegistered(Connection connection, NewUserInfo user) throws SQLException {
        boolean result = false;

        String sql = "SELECT * FROM users WHERE login=? AND password=? AND name=? AND surname=? AND email=?";

        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserName());
        ps.setString(4, user.getUserSurname());
        ps.setString(5, user.getEmail());

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            result = true;
        }
        return result;
    }

    public boolean isLoginNotUsed(Connection connection, NewUserInfo user) throws DaoException {
        boolean result = true;

        String sql = "SELECT * FROM users WHERE login=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }

    public boolean isEmailNotUsed(Connection connection, NewUserInfo user) throws DaoException {
        boolean result = true;
        String sql = "SELECT * FROM users WHERE email=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return result;
    }
}