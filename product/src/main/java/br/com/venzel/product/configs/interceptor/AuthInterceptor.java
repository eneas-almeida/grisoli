package br.com.venzel.product.configs.interceptor;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import br.com.venzel.product.modules.token.services.TokenService;
import br.com.venzel.product.shared.exceptions.business.ValidationException;

public class AuthInterceptor implements HandlerInterceptor {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TRANSACTION_ID = "transactionid";

    @Autowired
    private TokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        if (isOptions(request)) {
            return true;
        }

        if (isEmpty(request.getHeader(TRANSACTION_ID))) {
            throw new ValidationException("The transactionid header is required.");
        }
        
        String authorization = request.getHeader(AUTHORIZATION);
        
        tokenService.validateAuthorization(authorization);

        request.setAttribute("serviceid", UUID.randomUUID().toString());
        
        return true;
    }

    private boolean isOptions(HttpServletRequest request) {
        return HttpMethod.OPTIONS.name().equals(request.getMethod());
    }
}