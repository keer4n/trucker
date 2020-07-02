package io.github.keer4n.trucker.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        System.out.println("[REQUEST]: " + request.getProtocol() + request.getMethod() + " "
        + request.getRequestURI());
        return true;
    }
}
