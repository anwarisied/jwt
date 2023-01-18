package com.rm.roadmaps.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.rm.roadmaps.exception.EntityNotFoundException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class ExceptionHandlerFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (JWTVerificationException exception) {
            responseException(response, HttpServletResponse.SC_FORBIDDEN, "JWT NOT VALID");
        } catch (EntityNotFoundException exception) {
            responseException(response, HttpServletResponse.SC_NOT_FOUND, "Email does not exists");
        } catch (RuntimeException exception) {
            responseException(response, HttpServletResponse.SC_BAD_REQUEST, "Invalid parameters");
        }
    }

    private void responseException(HttpServletResponse response, int status, String msg) throws IOException {
        response.setStatus(status);
        response.getWriter().write(msg);
        response.getWriter().flush();
    }
}
