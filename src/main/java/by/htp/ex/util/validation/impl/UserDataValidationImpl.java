package by.htp.ex.util.validation.impl;

import by.htp.ex.bean.ErrorsMessages;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserDataValidationImpl implements UserDataValidation {
    public List<String> errorsMessagesDataValidation = new ArrayList<>();
    private final String LOG_PAS_CHECK = "[A-Z a-z 0-9]+";

    @Override
    public boolean checkAuthData(String login, String password) {
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
    public boolean checkRegData(NewUserInfo user) {
        boolean result = false;
        if (nameIsCorrect(user) && surNameIsCorrect(user) && loginIsCorrect(user)
                && passwordIsCorrect(user) && emailIsCorrect(user)) {
            result = true;
        }
        return result;
    }

    public boolean nameIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (!user.getUserName().isEmpty() && nameIsNormal(user)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.NAME_ENTERED_INCORRECTLY);
        }
        return result;
    }

    public boolean nameIsNormal(NewUserInfo user) {
        boolean result = false;
        if (Pattern.matches("[A-Z a-z]+", user.getUserName())) {
            result = true;
        }
        return result;
    }

    public boolean surNameIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (!user.getUserSurname().isEmpty() && surNameIsNormal(user)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.SURNAME_ENTERED_INCORRECTLY);
        }
        return result;
    }

    public boolean surNameIsNormal(NewUserInfo user) {
        boolean result = false;
        if (Pattern.matches("[A-Z a-z]+", user.getUserSurname())) {
            result = true;
        }
        return result;
    }

    @Override
    public List<String> getErrorsListMessage(String message) {
        errorsMessagesDataValidation.add(message);
        System.out.println(errorsMessagesDataValidation);
        return errorsMessagesDataValidation;
    }

    public boolean loginIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (!user.getLogin().isEmpty() || loginIsNormal(user)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.LOGIN_ENTERED_INCORRECTLY);
        }
        return result;
    }

    public boolean loginIsNormal(NewUserInfo user) {
        boolean result = false;
        if (Pattern.matches(LOG_PAS_CHECK, user.getLogin())) {
            result = true;
        }
        return result;
    }

    public boolean passwordIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (!user.getPassword().isEmpty() || passwordIsNormal(user)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.PASSWORD_NOT_ENTERED);
        }
        return result;
    }

    public boolean passwordIsNormal(NewUserInfo user) {
        boolean result = false;
        if (Pattern.matches(LOG_PAS_CHECK, user.getPassword())) {
            result = true;
        }
        return result;
    }

    public boolean emailIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (!user.getEmail().isEmpty() || emailIsNormal(user)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.EMAIL_ENTERED_INCORRECTLY);
        }
        return result;
    }

    public boolean emailIsNormal(NewUserInfo user) {
        boolean result = false;
        if (Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", user.getEmail())) {
            result = true;
        }
        return result;
    }

}



