package th.mine.employeesbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends Exception{
    private final static long serialVersionUID = 1L;

    public  EntityNotFoundException(String message){
        super(message);
    }
}
