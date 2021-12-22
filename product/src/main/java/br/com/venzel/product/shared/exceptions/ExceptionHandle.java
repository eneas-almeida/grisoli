package br.com.venzel.product.shared.exceptions;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.venzel.product.shared.exceptions.problems.AuthorizationException;
import br.com.venzel.product.shared.exceptions.problems.EntityAlreadyExistsException;
import br.com.venzel.product.shared.exceptions.problems.EntityInUseException;
import br.com.venzel.product.shared.exceptions.problems.EntityNotFoundException;
import br.com.venzel.product.shared.exceptions.problems.ValidationException;

@RestControllerAdvice
public class ExceptionHandle extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
    
    private Problem createProblemBuilder(HttpStatus status, ProblemType problemType, String detail) {
        
        return Problem
			.builder()
            .status(status.value())
            .type(problemType.getUri())
            .title(problemType.getTitle())
            .detail(detail)
            .build();
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception e, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (body == null) {

            body = Problem
				.builder()
                .title(status.getReasonPhrase())
                .status(status.value())
                .build();
        } else if (body instanceof String) {

            body = Problem
				.builder()
                .title((String) body)
                .status(status.value())
                .build();
        }

        return super.handleExceptionInternal(e, body, headers, status, request);
    }

	private ResponseEntity<Object> handleValidationInternal(Exception e, HttpHeaders headers, HttpStatus status, WebRequest request, BindingResult bindingResult) {
		
		String detail = "Um ou mais campos estão inválidos.";

		List<Problem.Field> objects = bindingResult
			.getAllErrors()
			.stream()
			.map(o -> 
				{
					String message = messageSource.getMessage(o, LocaleContextHolder.getLocale());
					
					String name = o.getObjectName();
					
					if (o instanceof FieldError) {
						name = ((FieldError) o).getField();
					}
					
					return Problem.Field
						.builder()
						.field(name)
						.message(message)
						.build();
				})
			.collect(Collectors.toList());

		Problem problem = Problem
			.builder()
			.status(HttpStatus.BAD_REQUEST.value())
			.detail(detail)
			.fields(objects)
			.build();

		return handleExceptionInternal(e, problem, headers, status, request);
	}

	// Exception

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleImcaughtException(Exception e, WebRequest request) {
		
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_IN_SYSTEM;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// EntityNotFoundException

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		ProblemType problemType = ProblemType.ENTITY_NOT_FOUND;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// EntityInUseException

	@ExceptionHandler(EntityInUseException.class)
	public ResponseEntity<?> handleEntityInUseFoundException(EntityInUseException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.CONFLICT;
		ProblemType problemType = ProblemType.ENTITY_IN_USER;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// EntityAlreadyExistsException

    @ExceptionHandler(EntityAlreadyExistsException.class)
	public ResponseEntity<?> handleEntityAlreadyExistsException(EntityAlreadyExistsException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.ALREADY_REPORTED;
		ProblemType problemType = ProblemType.ENTITY_IN_USER;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// AuthorizationException

    @ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<?> handleUserAuthorizationException(AuthorizationException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		ProblemType problemType = ProblemType.UNAUTHORIZED;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// ValidationException

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> handleValidationException(ValidationException e, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ProblemType problemType = ProblemType.VALIDATION;
		String detail = e.getMessage();
		
		Problem problem = createProblemBuilder(status, problemType, detail);
		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}

	// MethodArgumentNotValidException

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
	HttpHeaders headers, HttpStatus status, WebRequest request) {
				
		return handleValidationInternal(e, headers, status, request, e.getBindingResult());
	}
}