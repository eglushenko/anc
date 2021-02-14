package ua.anc.test.application.exception.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorInfo {
    private final HttpStatus status;
    private final Class exceptionClass;
    private final String msg;

    public ErrorInfo(HttpStatus status, Class exceptionClass, String msg) {
        this.status = status;
        this.exceptionClass = exceptionClass;
        this.msg = msg;
    }
}