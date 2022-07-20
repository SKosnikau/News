package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;

public interface UserService {
    boolean authorization (String login, String password) throws ServiceException;
    boolean registration(NewUserInfo user) throws ServiceException;
}
