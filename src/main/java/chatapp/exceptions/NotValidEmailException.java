package chatapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason="This email is not valid")
public class NotValidEmailException extends Exception {
    public NotValidEmailException() {super();}
}