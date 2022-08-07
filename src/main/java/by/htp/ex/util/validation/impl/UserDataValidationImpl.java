package by.htp.ex.util.validation.impl;

import by.htp.ex.bean.ErrorsMessages;
import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.service.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;

import java.util.ArrayList;
import java.util.List;

public class UserDataValidationImpl implements UserDataValidation {
    public List<String> errorsMessagesDataValidation = new ArrayList<>();

    private final String SYMBOL_COMMERCIAL_AT = "@";
    private final String SYMBOL_DOT = ".";


    @Override
    public boolean checkAuthData(String login, String password) {
        boolean result = false;
        if (login != null & password != null) {
            result = true;
        }
        return result;
    }

    @Override
    public boolean checkRegData(NewUserInfo user) throws ServiceException {
        boolean result = false;
        if (nameExists(user) & surNameExists(user) & loginExists(user) & passwordExists(user) & emailIsCorrect(user)) {
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

    public boolean nameExists(NewUserInfo user) {
        boolean result = false;
        if (user.getUserName() != null) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.NAME_NOT_ENTERED);
        }
        return result;
    }

    public boolean surNameExists(NewUserInfo user) {
        boolean result = false;
        if (user.getUserSurname() != null) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.SURNAME_NOT_ENTERED);
        }
        return result;
    }

    public boolean loginExists(NewUserInfo user) {
        boolean result = false;
        if (user.getLogin() != null) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.LOGIN_NOT_ENTERED);
        }
        return result;
    }

    public boolean emailIsCorrect(NewUserInfo user) {
        boolean result = false;
        if (emailExists(user) & isEmailRight(user)) {
            result = true;
        }
        return result;
    }

    public boolean isEmailRight(NewUserInfo user) {
        boolean result = false;

        if (user.getEmail().contains(SYMBOL_COMMERCIAL_AT) && user.getEmail().contains(SYMBOL_DOT)) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.EMAIL_ENTERED_INCORRECTLY);
        }
        return result;
    }

    public boolean passwordExists(NewUserInfo user) {
        boolean result = false;
        if (user.getPassword() != null) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.PASSWORD_NOT_ENTERED);
        }
        return result;
    }

    public boolean emailExists(NewUserInfo user) {
        boolean result = false;
        if (user.getEmail() != null) {
            result = true;
        } else {
            getErrorsListMessage(ErrorsMessages.EMAIL_NOT_ENTERED);
        }
        return result;
    }
}




//public class UserDataValidationImpl implements UserDataValidation{
//
//    public List <String> errorsMessagesDataValidation = new ArrayList<>();
//    private final String NAME_AND_SURNAME_CHECK = "[A-Z a-z]+";
//    private final String LOGIN_AND_PASWORD_CHECK = "[A-Z a-z 0-9]+";
//    private final String EMAIL_CHECK = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
//
//    //private final String SYMBOL_COMMERCIAL_AT = "@";
//    //private final String SYMBOL_DOT = ".";
//
//    @Override
//    public boolean checkAuthData(String login, String password){
//        boolean result = false;
//
//        if(password.isEmpty()||login.isEmpty()){
//            result = false;
//        }
//        if (Pattern.matches(LOGIN_AND_PASWORD_CHECK, password)
//                && Pattern.matches(LOGIN_AND_PASWORD_CHECK, login)) {
//            result = true;
//        }
//        return result;
//    }
//
//    @Override
//    public boolean checkRegData (NewUserInfo user) throws ServiceException {
//        boolean result = false;
//        if (nameIsCorrect (user) && surNameIsCorrect(user) && loginIsCorrect(user)
//                && passwordIsCorrect (user) && emailIsCorrect (user)) {
//            result =true;
//        }
//        return result;
//    }
//
//    public List<String> getErrorsListMessage(String message){
//        errorsMessagesDataValidation.add ( message);
//        System.out.println (errorsMessagesDataValidation);
//        return errorsMessagesDataValidation;
//    }
//
//    public boolean nameIsNormal (NewUserInfo user) {
//        boolean result = false;
//        if (Pattern.matches(NAME_AND_SURNAME_CHECK, user.getUsername())) {
//            result = true;
//        }
//        return result;
//    }
//
//    public boolean nameIsCorrect (NewUserInfo user) {
//        boolean result = false;
//        if (!user.getUsername().isEmpty() && nameIsNormal(user)) {
//            result = true;
//        }
//        else {
//            getErrorsListMessage(ErrorsMessage.NAME_ENTERED_INCORRECTLY);
//            //throw new ServiceException("Name not entered");
//        }
//        return result;
//    }
//
//    public boolean surNameIsNormal (NewUserInfo user) {
//        boolean result = false;
//        if (Pattern.matches(NAME_AND_SURNAME_CHECK, user.getUserSurname())) {
//            result = true;
//        }
//        return result;
//    }
//
//    public boolean surNameIsCorrect (NewUserInfo user) {
//        boolean result = false;
//        if (!user.getUserSurname().isEmpty() && surNameIsNormal (user)) {
//            result = true;
//        }
//        else {
//            getErrorsListMessage(ErrorsMessage.SURNAME_ENTERED_INCORRECTLY);
//            // throw new ServiceException("Surname not entered");
//        }
//        return result;
//    }
//
//    public boolean loginIsNormal (NewUserInfo user) {
//        boolean result = false;
//        if (Pattern.matches(LOGIN_AND_PASWORD_CHECK, user.getLogin())) {
//            result = true;
//        }
//        return result;
//    }
//
//    public boolean loginIsCorrect (NewUserInfo user) {
//        boolean result = false;
//        if (!user.getLogin().isEmpty() || loginIsCorrect (user)) {
//            result = true;
//        }
//        else {
//            getErrorsListMessage(ErrorsMessage.LOGIN_ENTERED_INCORRECTLY);
//            //throw new ServiceException("Login not entered");
//        }
//        return result;
//    }
//
//    public boolean passwordIsNormal (NewUserInfo user) {
//        boolean result = false;
//        if (Pattern.matches(LOGIN_AND_PASWORD_CHECK, user.getPassword())) {
//            result = true;
//        }
//        return result;
//    }
//
//    public boolean passwordIsCorrect (NewUserInfo user) {
//        boolean result = false;
//        if (!user.getPassword().isEmpty() ||passwordIsNormal (user)) {
//            result = true;
//        }
//        else {
//            getErrorsListMessage(ErrorsMessage.PASSWORD_NOT_ENTERED);
//            //throw new ServiceException("Password not entered");
//        }
//        return result;
//    }
//
//    public boolean emailIsNormal (NewUserInfo user) {
//        boolean result = false;
//        if ((Pattern.matches(EMAIL_CHECK, user.getEmail()))) {
//            result = true;
//        }
//        return result;
//    }
//
//    public boolean emailIsCorrect (NewUserInfo user) {
//        boolean result = false;
//        if (!user.getEmail().isEmpty() || emailIsNormal ( user)) {
//            result = true;
//        }
//        else {
//            getErrorsListMessage(ErrorsMessage.EMAIL_ENTERED_INCORRECTLY);
//            //throw new ServiceException("Email not entered");
//        }
//        return result;
//    }
//}