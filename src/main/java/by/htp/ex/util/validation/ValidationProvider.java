package by.htp.ex.util.validation;

public class ValidationProvider {
    private static final ValidationProvider instance = new ValidationProvider();

    private ValidationProvider() {

    }

    public static ValidationProvider getInstance() {
        return instance;
    }

    private final UserDataValidation userDataValidation = new UserDataValidationImpl();

    public UserDataValidation getUserDataValidation() {
        return userDataValidation;
    }
}
