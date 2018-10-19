package chatapp.validators;

import chatapp.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserValidator {
    public static final String VALID_NAME_REGEX = "[A-Za-z0-9]+";
    public static final String VALID_EMAIL_ADDRESS_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    public static final String VALID_PHONE_NO_REGEX = "\\+?[0-9]{10,16}";
    public static final String NOTIFICATION_TYPE_EMAIL = "email";
    public static final String NOTIFICATION_TYPE_PHONE = "phone";
    private static final int phoneNoMaxLength = 16;
    private static final int phoneNoMinLength = 10;

    public UserValidator(){}

    public boolean validate(User us) {
        return validName(us.getUserName()) && validEmail(us.getEmail()) && validPhoneNo(us.getPhoneNo())
                         && validNotificationType(us.getNotificationType());
    }

    public boolean validName(String name) {
        return name.matches(VALID_NAME_REGEX);
    }

    public boolean validNotificationType(String notificationType) {
        return notificationType.matches(NOTIFICATION_TYPE_EMAIL) ||
                notificationType.matches(NOTIFICATION_TYPE_PHONE);
    }

    public boolean validEmail(String email) {
        return email.matches(VALID_EMAIL_ADDRESS_REGEX);
    }

    public boolean validPhoneNo(String phoneNo) {
        return phoneNo.matches(VALID_PHONE_NO_REGEX) && phoneNo.length() <= phoneNoMaxLength
                                                     && phoneNo.length() >= phoneNoMinLength;
    }
}
