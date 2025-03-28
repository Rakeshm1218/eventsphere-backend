package com.project.eventsphereBackend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) throws Exception {
        var session = request.getSession(false);
        if (session == null) {
            response.setStatus(401);
            response.getWriter().write("UNAUTHORIZED from INTERCEPTOR");
            return false;
        }
        var usernameData = session.getAttribute("email");
        if (usernameData == null) {
            response.setStatus(401);
            response.getWriter().write("UNAUTHORIZED from INTERCEPTOR");
            return false;
        }
        return true;
    }

}