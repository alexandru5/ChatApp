package chatapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason="This username is already in the system")
public class UsernameExistsException extends Exception {
    public UsernameExistsException() {super();}
}
