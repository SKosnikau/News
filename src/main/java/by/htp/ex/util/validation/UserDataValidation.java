package by.htp.ex.util.validation;

import by.htp.ex.bean.NewUserInfo;

public interface UserDataValidation {
    boolean checkAuthData(String login, String password);

    boolean checkRegData(NewUserInfo user);
}
