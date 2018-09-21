package chatapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, code = HttpStatus.BAD_REQUEST, reason="This privilege is not found in the system")
public class PrivilegeNotFoundException extends Exception {

    public PrivilegeNotFoundException() {super();}
}