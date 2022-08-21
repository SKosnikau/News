package by.htp.ex.util.validation.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;

import java.util.regex.Pattern;

public class UserDataValidationImpl implements UserDataValidation {
    private final String LOG_PAS_CHECK = "[A-Z a-z 0-9]+";
    private final String EMAIL_CHECK = "\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*\\.\\w{2,4}";
    private final String NAME_AND_SURNAME_CHECK = "[A-Z a-z]+";


    @Override
    public boolean checkAuthData(String login, String password) throws ServiceException {
        boolean result = false;
        if (password.isEmpty() || login.isEmpty()) {
            result = false;
        }
        if (Pattern.matches(LOG_PAS_CHECK, password) && Pattern.matches(LOG_PAS_CHECK, login)) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkRegData(NewUserInfo user) throws ServiceException {
        boolean result = false;
        if (nameIsCorrect(user) && surNameIsCorrect(user) && loginIsCorrect(user)
                && passwordIsCorrect(user) && emailIsCorrect(user)) {
            result = true;
        }
        return result;
    }

    private boolean nameIsCorrect(NewUserInfo user) {

        if (user.getUserName().isEmpty() || !nameIsNormal(user)) {
            return false;
        }
        return true;
    }

    private boolean nameIsNormal(NewUserInfo user) {
        return Pattern.matches(NAME_AND_SURNAME_CHECK, user.getUserName());
    }

    private boolean surNameIsCorrect(NewUserInfo user) {
        if (user.getUserSurname().isEmpty() || !surNameIsNormal(user)) {
            return false;
        }
        return true;
    }

    private boolean surNameIsNormal(NewUserInfo user) {
        return Pattern.matches(NAME_AND_SURNAME_CHECK, user.getUserSurname());
    }

    private boolean loginIsCorrect(NewUserInfo user) {
        if (user.getLogin().isEmpty() || !loginIsNormal(user)) {
            return false;
        }
        return true;
    }

    private boolean loginIsNormal(NewUserInfo user) {
        return Pattern.matches(LOG_PAS_CHECK, user.getLogin());
    }

    private boolean passwordIsCorrect(NewUserInfo user) {
        if (user.getPassword().isEmpty() || !passwordIsNormal(user)) {
            return false;
        }
        return true;
    }

    private boolean passwordIsNormal(NewUserInfo user) {
        return (Pattern.matches(LOG_PAS_CHECK, user.getPassword()));
    }

    private boolean emailIsCorrect(NewUserInfo user) {
        if (user.getEmail().isEmpty() || !emailIsNormal(user)) {
            return false;
        }
        return true;
    }

    private boolean emailIsNormal(NewUserInfo user) {
        return (Pattern.compile(EMAIL_CHECK).matcher(user.getEmail()).matches());
    }
}