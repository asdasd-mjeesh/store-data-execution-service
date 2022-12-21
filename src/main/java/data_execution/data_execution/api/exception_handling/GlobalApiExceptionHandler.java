package data_execution.data_execution.api.exception_handling;

import data_execution.data_execution.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalApiExceptionHandler {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    public ResponseEntity<Object> entityNotFoundHandler(EntityNotFoundException e) {
        var responseStatus = HttpStatus.NOT_FOUND;
        var exceptionDetails = ExceptionResponseDetails.builder()
                .message(e.getMessage())
                .exception(e.getCause())
                .httpStatus(responseStatus)
                .time(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(exceptionDetails, responseStatus);
    }
}
