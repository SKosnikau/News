package by.htp.ex.service;

public class ServiceException extends Exception {

    public ServiceException(String e) {
        super(e);
    }

    public ServiceException() {
        super();
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception exception) {
        super(message, exception);
    }
}