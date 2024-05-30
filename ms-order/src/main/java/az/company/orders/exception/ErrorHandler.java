package az.company.orders.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static az.company.orders.model.enums.ErrorMessage.SERVER_ERROR;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.SERVICE_UNAVAILABLE;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(MethodArgumentNotValidException exception) {
        return ErrorResponse.builder()
                .message(exception.getBindingResult().getFieldError().getDefaultMessage())
                .build();
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(SERVICE_UNAVAILABLE)
    public ErrorResponse handle(ServiceUnavailableException exception) {
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(CustomFeignException.class)
    public ResponseEntity<ErrorResponse> handle(CustomFeignException exception) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(ErrorResponse.builder()
                        .message(exception.getMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handle(Exception exception) {
        return ErrorResponse.builder()
                .message(SERVER_ERROR.getMessage())
                .build();
    }
}
