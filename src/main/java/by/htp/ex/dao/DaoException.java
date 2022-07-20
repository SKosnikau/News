package by.htp.ex.dao;

import java.io.Serial;

public class DaoException extends Exception {
    @Serial
    private static final long serialVersionUID = 6869821102553976071L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Exception e) {
        super(message, e);
    }

    public DaoException(Exception e) {
        super(e);
    }
}