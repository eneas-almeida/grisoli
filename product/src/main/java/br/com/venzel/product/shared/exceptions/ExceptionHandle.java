package br.com.venzel.product.shared.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.venzel.product.shared.exceptions.business.AuthorizationException;
import br.com.venzel.product.shared.exceptions.business.EntityAlreadyExistsException;
import br.com.venzel.product.shared.exceptions.business.EntityInUseException;
import br.com.venzel.product.shared.exceptions.business.EntityNotFoundException;
import br.com.venzel.product.shared.exceptions.business.ValidationException;

@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {
    
    private ExceptionBusiness createProblemBuilder(HttpStatus status, BusinessEnum problemType, String detail) {
        
        return ExceptionBusiness.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail)
            .build();
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {
            body = ExceptionBusiness.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .build();
        } else if (body instanceof String) {
            body = ExceptionBusiness.builder()
                .title((String) body)
                .status(status.value())
                .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		BusinessEnum problemType = BusinessEnum.ENTITY_NOT_FOUND;
		String detail = ex.getMessage();
		
		ExceptionBusiness problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUseFoundException(EntityInUseException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		BusinessEnum problemType = BusinessEnum.ENTITY_IN_USER;
		String detail = ex.getMessage();
		
		ExceptionBusiness problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

    @ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<?> handleEntityAlreadyExistsException(EntityAlreadyExistsException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.ALREADY_REPORTED;
		BusinessEnum problemType = BusinessEnum.ENTITY_IN_USER;
		String detail = ex.getMessage();
		
		ExceptionBusiness problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

    @ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<?> handleUserAuthorizationException(AuthorizationException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		BusinessEnum problemType = BusinessEnum.UNAUTHORIZED;
		String detail = ex.getMessage();
		
		ExceptionBusiness problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		BusinessEnum problemType = BusinessEnum.VALIDATION;
		String detail = ex.getMessage();
		
		ExceptionBusiness problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
}