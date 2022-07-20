package by.htp.ex.service;

import by.htp.ex.bean.NewUserInfo;

public interface AdminService {
    void block(NewUserInfo user)throws ServiceException;
    boolean edit(String text) throws ServiceException;
    boolean add(String text)throws ServiceException;
    boolean delete(String text)throws ServiceException;
}
