package com.pwz.filter;

import com.pwz.myGenerator.Log;
import com.pwz.util.ExceptionUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/1 13:41
 **/
@WebFilter
public class MyFilter implements Filter {
    private Log log = new Log("MyServlet");
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("自定义过滤器初始化");
        log.write2Path();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("自定义过滤操作");
        try {
            chain.doFilter(request, response);
        } catch (Exception e){
            String content = ExceptionUtil.getContent(e);
            log.info(content);
        }finally {
            log.write2Path();
        }

    }

    @Override
    public void destroy() {
        log.info("自定义过滤器销毁操作");
        log.write2Path();
    }
}
