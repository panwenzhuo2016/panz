package com.pwz.execution;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/9/23 20:04
 **/
public class WebExecution extends RuntimeException {

    private static final long serialVersionUID = -7537395265357977272L;

    public WebExecution() {
        super();
    }

    public WebExecution(String message) {
        super(message);
    }

    public WebExecution(String message, Throwable cause) {
        super(message, cause);
    }

    public WebExecution(Throwable cause) {
        super(cause);
    }
}
