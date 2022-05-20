package oasip.backend.Validation;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.*;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, Object> response = new HashMap<String, Object>();
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName , message);
        });

        final Map<String, Object> body = new LinkedHashMap<>();
        final String path = request.getDescription(false);
        body.put("timestamp", new Date());
        body.put("status", status.value());
        body.put("error", HttpStatus.BAD_REQUEST);
        body.put("message", errors);
        body.put("path", path.substring(4));
        return new ResponseEntity<Object>(body, HttpStatus.BAD_REQUEST);
    }
}
