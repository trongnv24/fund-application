package aibles.java.spring.boot.fund.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private LocalDateTime timestamp;
    private String message;

    public ExceptionResponse(LocalDateTime timestamp, String message) {
        super();
        this.timestamp = timestamp;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }


}
