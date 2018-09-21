package chatapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason="This phoneNo is not valid")
public class NotValidPhoneNoException extends Exception {
    public NotValidPhoneNoException() {super();}
}