package by.htp.ex.util.validation;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.ServiceException;

import java.util.List;

public interface UserDataValidation {
    boolean checkAuthData(String login, String password) throws SecurityException;
    boolean checkRegData(NewUserInfo user) throws ServiceException;
    public List<String> getErrorsListMessage(String message);
}
