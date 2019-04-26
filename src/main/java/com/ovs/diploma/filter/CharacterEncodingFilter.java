package com.ovs.diploma.filter;

import org.springframework.context.annotation.ComponentScan;

import javax.servlet.*;
import java.io.IOException;

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
        System.out.println(request.getAttributeNames());
        System.out.println(request.getContentType());
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

}
