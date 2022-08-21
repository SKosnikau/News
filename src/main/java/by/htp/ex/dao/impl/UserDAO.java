package by.htp.ex.dao.impl;

import java.sql.*;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.UsersRole;
import by.htp.ex.dao.DaoException;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;

public class UserDAO implements IUserDAO {
    private static final String lOGIN_AND_PASSWORD_CHECK = "SELECT * FROM users WHERE login=? AND password=?";
    private static final String ROLE_SEARCH = "SELECT * FROM users JOIN roles ON users.id_roles = roles.id_roles WHERE login=? AND password=?";
    private static final String ADDING_USER = "INSERT INTO users(login,password,name,surname,email,id_roles) values (?,?,?,?,?,?)";
    private int idRole;
    private String role;
    private static final String SEARCH_ROLE_ID = "SELECT * FROM roles WHERE title=?";
    private static final String LOGIN_VERIFICATION = "SELECT * FROM users WHERE login=?";
    private static final String EMAIL_VERIFICATION = "SELECT * FROM users WHERE email=?";

    @Override
    public boolean logination(String login, String password) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(lOGIN_AND_PASSWORD_CHECK)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            return (rs.next());

        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public String getRole(String login, String password) throws DaoException {
        String role;
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(ROLE_SEARCH)) {
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                role = rs.getString(DatabaseTableColumn.TABLE_ROLES_COLUMN_TITLE);
                return role;
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return UsersRole.GUEST.getTitle();
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
        try (Connection connection = ConnectionPool.getInstance().takeConnection();
             PreparedStatement ps = connection.prepareStatement(ADDING_USER)) {
            RegistrationCheckInDB registrationCheckInDB = new RegistrationCheckInDB(connection, user);


            if (!registrationCheckInDB.isCorrect()) {
                return false;
            }
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserName());
            ps.setString(4, user.getUserSurname());
            ps.setString(5, user.getEmail());

            int idRole = getIdRoleByTitle(connection, UsersRole.USER.getTitle());
            ps.setInt(6, idRole);

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            throw new DaoException(e);
        } catch (ConnectionPoolException e) {
            throw new DaoException(e);
        }

    }

    private int getIdRoleByTitle(Connection connection, String role) throws SQLException {
        int idRole = 3;
        PreparedStatement ps = connection.prepareStatement(SEARCH_ROLE_ID);
        ps.setString(1, role);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            idRole = rs.getInt(DatabaseTableColumn.TABLE_ROLES_COLUMN_ID_ROLES);
        }
        return idRole;
    }


    private class RegistrationCheckInDB {

        private Connection connection;
        private NewUserInfo user;
        private boolean correct;

        public RegistrationCheckInDB() {
        }

        public RegistrationCheckInDB(Connection connection, NewUserInfo user) throws SQLException {
            this.connection = connection;
            this.user = user;
            this.correct = isCanBeRegistered(connection, user);
        }

        public boolean isCorrect() {
            return correct;
        }

        private boolean isCanBeRegistered(Connection connection, NewUserInfo user) throws SQLException {
            if (isEmailUsed(connection, user) || isLoginUsed(connection, user)) {
                return false;
            }
            return true;
        }

        public boolean isLoginUsed(Connection connection, NewUserInfo user) throws SQLException {
            PreparedStatement ps = connection.prepareStatement(LOGIN_VERIFICATION);
            ps.setString(1, user.getLogin());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }

        public boolean isEmailUsed(Connection connection, NewUserInfo user) throws SQLException {
            PreparedStatement ps = connection.prepareStatement(EMAIL_VERIFICATION);
            ps.setString(1, user.getEmail());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }
}