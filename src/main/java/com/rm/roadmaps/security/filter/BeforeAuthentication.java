package com.rm.roadmaps.security.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class BeforeAuthentication implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String url=((HttpServletRequest)servletRequest).getRequestURI();
        System.out.println(url);
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
