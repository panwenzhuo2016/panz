package com.pwz.servlet;

import com.pwz.myGenerator.Log;
import com.pwz.util.ExceptionUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/1 12:35
 **/
@WebServlet(urlPatterns = "/*")
public class MyServlet extends HttpServlet {
    private DispatcherServlet dispatcherServlet;
    private ApplicationContext ctx;
    private Log log = new Log("MyServlet");

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            super.init(config);
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(config.getServletContext());
            dispatcherServlet = ctx.getBean(DispatcherServlet.class);
            dispatcherServlet.init(config);
        } catch (Exception e){
            final String content = ExceptionUtil.getContent(e);
            log.info(content);
            throw e;
        }finally {
            log.write2Path();
        }

    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            dispatcherServlet.service(req, resp);
        } catch (Exception e) {
            final String content = ExceptionUtil.getContent(e);
            log.info(content);
            throw e;
        } finally {
            log.write2Path();
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
