package io.github.keer4n.trucker.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("[INTERCEPTED]: " + request.getMethod() + " "
        + request.getRequestURI() + " "
        + request.getProtocol() + " " + request.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
        return true;
    }
}
