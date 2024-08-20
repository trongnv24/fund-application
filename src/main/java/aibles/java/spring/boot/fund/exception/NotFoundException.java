package aibles.java.spring.boot.fund.exception;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class NotFoundException extends ResourceNotFoundException{
    public NotFoundException(String message) {
        super(message);
    }

}

