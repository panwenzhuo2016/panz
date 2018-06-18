package com.pwz.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by hasee on 2018/6/18.
 */
public class Filter3 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Filter3 init");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Filter3 doFilter");
        chain.doFilter(request,response);

    }

    @Override
    public void destroy() {
        System.out.println("Filter3 destroy");
    }
}
