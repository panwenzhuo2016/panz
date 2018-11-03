package com.pwz.filter.e;

/**
 * 类说明：
 *
 * @Author panwenzhuo
 * @Date created in 2018/10/1 16:28
 **/
public class Filter1 implements Filter {

    @Override
    public void filter(FilterChain chain) {
        System.out.println("Filter1------------");
        chain.doFilter();
    }
}
