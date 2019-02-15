package com.ovs.diploma.filter;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

//@Component
public class CharacterEncodingFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(" ");
        System.out.println(" ");
        System.out.println(" jkk ");
        System.out.println(" ");
        System.out.println(" ");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
