package data_execution.data_execution.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class ExceptionResponseDetails {
    private final String message;
    private final Throwable exception;
    private final HttpStatus httpStatus;
    private final LocalDateTime time;
}
